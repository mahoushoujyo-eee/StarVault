package org.example.starvault.entities;

import lombok.Data;
import stark.coderaider.fluentschema.commons.NamingConvention;
import stark.coderaider.fluentschema.commons.annotations.AutoIncrement;
import stark.coderaider.fluentschema.commons.annotations.Column;
import stark.coderaider.fluentschema.commons.annotations.PrimaryKey;
import stark.coderaider.fluentschema.commons.annotations.Table;

@Data
@Table(namingConvention =  NamingConvention.LOWER_CASE_WITH_UNDERSCORE)
public class HistoricalFile
{
    @PrimaryKey
    @AutoIncrement
    private Long id;

    @Column(type = "BIGINT")
    private Long fileId;

    @Column(type = "BIGINT")
    private Long snapshotId;

    @Column(type = "VARCHAR(50)")
    private String name;

    @Column(type = "VARCHAR(100)")
    private String url;

    @Column(type = "BIGINT")
    private Long directoryId;

    @Column(type = "BIGINT")
    private Long userId;
}
