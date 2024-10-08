package com.loja.projetolojaweb2.service;

import com.loja.projetolojaweb2.Exceptions.FileStorageException;
import com.loja.projetolojaweb2.Exceptions.MyFileNotFoundException;
import com.loja.projetolojaweb2.config.FileStorageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        this.fileStorageLocation = path;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(
                    "Não foi possível criar o diretório onde os arquivos enviados serão armazenados!", e);
        }
    }
    public String storeFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
// Filename..txt
            if (filename.contains("..")) {
                throw new FileStorageException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida"+ filename);}
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);return filename;
        } catch (Exception e) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + filename + ". Por favor, tente novamente!", e);}
    }
    public Resource loadFileAsResource(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists())
                return resource;
            else
                throw new MyFileNotFoundException("File not found");
        } catch (Exception e) {
            throw new MyFileNotFoundException("File not found" + filename, e);}
    }
}