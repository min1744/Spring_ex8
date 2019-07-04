package com.iu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	public String saveFile(String realPath, MultipartFile multipartFile) throws Exception{
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();//s가 뒤에 붙은 것은 resources도 없을 때 만들어 줌
		}
		String fileSystemName = UUID.randomUUID().toString();
		String originalName = multipartFile.getOriginalFilename();
		originalName = originalName.substring(originalName.lastIndexOf("."));
		fileSystemName = fileSystemName+originalName;
		file = new File(realPath, fileSystemName);
		multipartFile.transferTo(file);
		
		return fileSystemName;
	}
}