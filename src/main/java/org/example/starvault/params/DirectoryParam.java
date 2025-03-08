package org.example.starvault.params;

import lombok.Data;
import org.example.starvault.entities.Directory;

@Data
public class DirectoryParam
{
    private Long id;
    private String name;
    private String parentName;
    private Long parentId;
    private Long userId;

    public DirectoryParam(Long id)
    {
        this.id = id;
    }

    public DirectoryParam()
    {

    }
}
