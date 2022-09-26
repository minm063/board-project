package com.example.board.home;

import com.example.board.home.impl.BoardVO;
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
import java.util.UUID;

public class AttachedFile {

    private BasicFileAttributes attributes;

//    public void uploadFile(HttpServletRequest request, MultipartFile file) {
//        String temp = UUID.randomUUID().toString();
//        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
//                + "crud" + File.separator + temp + "_" + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
//
//        if (!Files.exists(serverPath)) {
//            try {
//                Files.createDirectories(serverPath.getParent());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            Files.copy(file.getInputStream(), serverPath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void getFile(BoardVO vo, HttpServletRequest request) {
        System.out.println(vo.getFileName());
        System.out.println(request.getAttribute(vo.getFileName()));
        Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                + "crud" + File.separator + vo.getFileName());
        System.out.println("serverPath : " + serverPath);
//        if (!Files.exists(serverPath)) {
//            return;
//        }

//        try {
//            attributes = Files.readAttributes(serverPath, BasicFileAttributes.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (attributes != null) {
//            FileTime fileTime = attributes.creationTime();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String formatFileTime = dateFormat.format(fileTime.toMillis());
//            System.out.println(formatFileTime);
//
//            if (formatFileTime.equals(vo.getRegdate().toString())) {
//                System.out.println("ok");
//            }
//        }
    }

    public void deleteFile(HttpServletRequest request, String names) {
        // fileName:null
        for (String fileName: names.split(",")) {
            Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                    + "crud" + File.separator + fileName);
            try {
                Files.deleteIfExists(serverPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFile(MultipartFile[] files, HttpServletRequest request, BoardVO vo) {
        StringBuilder names = new StringBuilder();
        if (vo.getFileName() != null) {
            names.append(vo.getFileName());
        }
        for (MultipartFile file : files) {
            String name = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path serverPath = Paths.get(request.getSession().getServletContext().getRealPath(File.separator) + File.separator
                    + "crud" + File.separator + name);
            System.out.println(serverPath);
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
            names.append(name).append(",");
        }
        vo.setFileName(names.toString());
        System.out.println(names);
    }
}
