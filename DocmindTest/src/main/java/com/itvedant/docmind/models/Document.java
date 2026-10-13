package com.itvedant.docmind.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_filename", nullable = false, length = 255)
    private String originalFilename;

    @Column(name = "storage_path", length = 1000)
    private String storagePath;

    @Column(name = "content_type", length = 150)
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User uploadedBy;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentChunk> chunks = new ArrayList<>();

    public Document() {
    }

  
    public Long getId() { 
    	return id;
    	}
    public void setId(Long id) { 
    	this.id = id;
    	}
    public String getOriginalFilename() {
    	return originalFilename;
    	}
    public void setOriginalFilename(String originalFilename) {
    	this.originalFilename = originalFilename;
    	}
    public String getStoragePath() { 
    	return storagePath; 
    	}
    public void setStoragePath(String storagePath) {
    	this.storagePath = storagePath;
    	}
    public String getContentType() {
    	return contentType; 
    	}
    public void setContentType(String contentType) { 
    	this.contentType = contentType;
    	}
    public Long getFileSize() {
    	return fileSize;
    	}
    public void setFileSize(Long fileSize) { 
    	this.fileSize = fileSize;
    	}
    
    public Instant getCreatedAt() {
    	return createdAt; 
    	}
    public void setCreatedAt(Instant createdAt) {
    	this.createdAt = createdAt;
    	}
    public User getUploadedBy() {
    	return uploadedBy;
    	}
    public void setUploadedBy(User uploadedBy) { 
    	this.uploadedBy = uploadedBy;
    	}
    public List<DocumentChunk> getChunks() {
    	return chunks;
    	}
    public void setChunks(List<DocumentChunk> chunks) {
    	this.chunks = chunks;
    	}

    public void addChunk(DocumentChunk chunk) {
        chunks.add(chunk);
        chunk.setDocument(this);
    }
}
