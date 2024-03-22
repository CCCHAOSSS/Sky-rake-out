package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author limei
 * @date 2024/3/22 14:10
 * @description 通用接口，图片有关
 */
@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api("通用接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){
        log.info("上传文件：{}", file);

        try{
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构造新的文件名
            String objectName = UUID.randomUUID().toString() + extension;//使用UUId防止文件重名覆盖旧文件

            //文件的请求路径
            String filepath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filepath);
        }catch (IOException e){
            e.printStackTrace();
            log.info("文件上传失败：{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
