package com.talk.talk.domain.commonFile;

import com.talk.talk.domain.BaseTimeEntity;
import com.talk.talk.vo.common.file.FileUploadResDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "COMMON_FILE")
public class CommonFile extends BaseTimeEntity {

    /**
     * File 순번
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_SEQ")
    private Long fileSeq;

    /**
     * 파일명
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * 원본 파일명
     * */
    @Column(name = "ORIGIN_FILE_NAME")
    private String originFileName;

    /**
     * 확장자 타입
     * */
    @Column(name = "CONTENT_TYPE")
    private String contentType;

    /**
     * 파일 확장자
     */
    @Column(name = "FILE_EXT")
    private String fileExt;

    /**
     * 파일경로
     */
    @Column(name = "FILE_PATH")
    private String filePath;

    /**
     * 등록자
     * */
    @Column(name = "CREATE_USER_SEQ")
    private Long createUserSeq;

    public FileUploadResDto entityToDto() {
        return FileUploadResDto.builder()
                .fileSeq(this.fileSeq)
                .fileName(this.fileName)
//                .filePath(this.filePath)
                .fileExt(this.fileExt)
                .build();
    }

}
