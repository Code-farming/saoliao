package com.lhb.saoliao.service.impl;

import com.lhb.saoliao.entity.Users;
import com.lhb.saoliao.dao.UsersDao;
import com.lhb.saoliao.service.UsersService;
import com.lhb.saoliao.utils.FastDFSClient;
import com.lhb.saoliao.utils.FileUtils;
import com.lhb.saoliao.utils.QRCodeUtils;
import org.n3r.idworker.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * (Users)表服务实现类
 *
 * @author LHb
 * @since 2020-03-22 13:42:03
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
    @Resource
    private UsersDao usersDao;

    @Resource
    private Sid sid;

    @Resource
    private QRCodeUtils qrCodeUtils;

    @Resource
    private FastDFSClient fastDFSClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Users queryById(String id) {
        return this.usersDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Users> queryAllByLimit(int offset, int limit) {
        return this.usersDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users insert(Users users) {
        this.usersDao.insert(users);
        return users;
    }

    /**
     * 修改数据
     *
     * @param users 实例对象
     * @return 实例对象
     */
    @Override
    public Users update(Users users) {
        this.usersDao.update(users);
        return this.queryById(users.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.usersDao.deleteById(id) > 0;
    }

    @Override
    public boolean queryByUserNameIsExist(String username) {
        return usersDao.queryByUsername(username) != null ? true : false;
    }

    @Override
    public Boolean login(String username, String password) {
        Users users = usersDao.queryByNameAndPwd(username, password);
        return users != null ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Integer saveUser(Users users) {
        String userId = sid.nextShort();
        //  为每个用户生成一个唯一的二维码
        users.setQrcode("");
        String qrCodePath = "D://user" + userId + "qrcode.png";
        // saoLiao_qrcode:[username]
        qrCodeUtils.createQRCode(qrCodePath, "saoLiao_qrcode:" + userId);
        MultipartFile qrCodeFile = FileUtils.fileToMultipart(qrCodePath);
        String qrCodeUrl = "";
        try {
            qrCodeUrl = fastDFSClient.uploadQRCode(qrCodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.setQrcode(qrCodeUrl);
        users.setId(userId);
        return usersDao.addUser(users);
    }

    @Override
    public Users getUserByName(String username) {
        return usersDao.queryByUsername(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(Users user) {
        usersDao.update(user);
        return usersDao.queryById(user.getId());
    }

}