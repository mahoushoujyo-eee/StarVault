package org.example.starvault.params;

import lombok.Data;

@Data
public class DirectoryParam
{
    private Long id;
    private String name;
    private String parentName;
    private Long parentId;
    private Long userId;
}
