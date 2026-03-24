package com.calvin.klein.data.utilityExternal.Interface;


import com.calvin.klein.application.Services.ResultService;
import com.calvin.klein.data.cloudinaryUtil.CloudinaryCreate;

import java.util.List;

public interface ICloudinaryUti {
    ResultService<CloudinaryCreate> CreateImg(String url, Integer width, Integer height);
    ResultService<String> deleteImg(List<String> publicList);
}
