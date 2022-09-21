package com.example.board.home;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AttachedFile {

    private BasicFileAttributes attributes;

    public void uploadFile(HttpServletRequest request, MultipartFile file) {
        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                + "crud" + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        System.out.println("serverPath : " + serverPath);
        if (!Files.exists(serverPath)) {
            try {
                Files.createDirectories(serverPath.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.copy(file.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFile(Date regDate, HttpServletRequest request) {
        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                + "crud" + File.separator);
        System.out.println("serverPath : " + serverPath);
        if (!Files.exists(serverPath)) {
            return;
        }
        try {
            attributes = Files.readAttributes(serverPath, BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (attributes != null) {
            FileTime fileTime = attributes.creationTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String formatFileTime = dateFormat.format(fileTime.toMillis());
            System.out.println(formatFileTime);

            if (formatFileTime.equals(regDate.toString())) {
                System.out.println("ok");
            }
        }
    }

    public void deleteFile(MultipartFile file, HttpServletRequest request)
    {
        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                + "crud" + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));

        try {
            Files.deleteIfExists(serverPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
