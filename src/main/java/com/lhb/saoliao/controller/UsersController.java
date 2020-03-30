package com.lhb.saoliao.controller;

import com.lhb.saoliao.entity.Users;
import com.lhb.saoliao.entity.bo.UsersBo;
import com.lhb.saoliao.entity.vo.UsersVo;
import com.lhb.saoliao.result.ServerResponse;
import com.lhb.saoliao.service.UsersService;
import com.lhb.saoliao.utils.FastDFSClient;
import com.lhb.saoliao.utils.FileUtils;
import com.lhb.saoliao.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * (Users)表控制层
 *
 * @author LHb
 * @since 2020-03-22 13:42:03
 */
@RestController
@RequestMapping("users")
@CrossOrigin
public class UsersController {
    /**
     * 服务对象
     */
    @Resource
    private UsersService usersService;

    /**
     * FastDFSClient对象
     */
    @Resource
    private FastDFSClient fastDFSClient;

    /**
     * @Description: 用户注册/登录
     */
    @PostMapping("registerOrLogin")
    public ServerResponse registerOrLogin(@RequestBody Users users) throws Exception {
        // 1.判断用户名和密码是否为空
        if (StringUtils.isBlank(users.getUsername()) || StringUtils.isBlank(users.getPassword())) {
            return ServerResponse.errorMsg("用户名或密码为空....");
        }
        // 2.判断用户名是否存在,如果存在则登录,不存在注册账号
        boolean nameIsExist = usersService.queryByUserNameIsExist(users.getUsername());
        if (nameIsExist) {
            // 2.1 登录
            Boolean login = usersService.login(users.getUsername(), MD5Utils.getMD5Str(users.getPassword()));
            if (login == false) {
                return ServerResponse.errorMsg("用户名或密码错误....");
            }
        } else {
            // 2.2 注册
            users.setFaceImage("");
            users.setFaceImageBig("");
            users.setNickname(users.getUsername());
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            usersService.saveUser(users);
        }
        // 根据用户名查找用户,并user中的password,cid属性置空
        Users user = usersService.getUserByName(users.getUsername());
        UsersVo usersVo = new UsersVo();
        BeanUtils.copyProperties(user, usersVo);
        return ServerResponse.ok(usersVo);
    }


    /**
     * @Description: 上传用户头像
     */
    @PostMapping("uploadFaceBase64")
    public ServerResponse uploadFaceBase64(@RequestBody UsersBo usersBo) throws Exception {
        // 获取前端传过来的base64字符串,然后转换为文件对象再上传
        String base64Data = usersBo.getFaceData();
        String userFacePath = "D:\\" + usersBo.getUserId() + "userface64.png";
        System.out.println(userFacePath);
        FileUtils.base64ToFile(userFacePath, base64Data);
        // 上传文件到fastdfs
        MultipartFile faceFile = FileUtils.fileToMultipart(userFacePath);
        String url = fastDFSClient.uploadBase64(faceFile);
        System.out.println(url);

//		"dhawuidhwaiuh3u89u98432.png"
//		"dhawuidhwaiuh3u89u98432_80x80.png"

        // 获取缩略图的url
        String thump = "_80x80.";
        String arr[] = url.split("\\.");
        String thumpImgUrl = arr[0] + thump + arr[1];

        // 更新用户头像
        Users user = new Users();
        user.setId(usersBo.getUserId());
        user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);

        // 保存数据库
        Users result = usersService.updateUserInfo(user);
        return ServerResponse.ok(result);
    }

    /**
     *
     * @param usersBo
     * @return
     */
    @PostMapping("setNickname")
    public ServerResponse setNickname(@RequestBody UsersBo usersBo) {
        Users user = new Users();
        user.setId(usersBo.getUserId());
        user.setNickname(usersBo.getNickname());

        Users result = usersService.updateUserInfo(user);
        return ServerResponse.ok(result);


    }
}