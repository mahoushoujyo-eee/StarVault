package org.example.starvault.params;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.starvault.entities.File;


public class FileChunkParam extends File
{
    private Integer chunkIndex;
    private String taskId;

    public Integer getChunkIndex() {
        return chunkIndex;
    }

    public void setChunkIndex(Integer chunkIndex) {
        this.chunkIndex = chunkIndex;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
