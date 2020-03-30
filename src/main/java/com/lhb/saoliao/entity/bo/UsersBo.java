package com.lhb.saoliao.entity.bo;

import lombok.Data;
import java.io.Serializable;

@Data
public class UsersBo implements Serializable {
    private static final long serialVersionUID = 6275496104173782684L;
    private String userId;
    private String faceData;
    private String nickname;
}
