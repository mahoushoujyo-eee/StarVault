package org.example.starvault.params;

import lombok.Data;

@Data
public class DirectoryAndFileParam
{
    private Long id;
    private Long userId;
    private Long parentId;
    private String name;
    private String url;
    private String type;
}
