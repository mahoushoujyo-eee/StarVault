package org.example.starvault.entities;

import lombok.Data;
import stark.coderaider.fluentschema.commons.NamingConvention;
import stark.coderaider.fluentschema.commons.annotations.AutoIncrement;
import stark.coderaider.fluentschema.commons.annotations.Column;
import stark.coderaider.fluentschema.commons.annotations.PrimaryKey;
import stark.coderaider.fluentschema.commons.annotations.Table;

import java.util.Date;

@Data
@Table(namingConvention =  NamingConvention.LOWER_CASE_WITH_UNDERSCORE)
public class Version
{
    @PrimaryKey
    @AutoIncrement
    private Long id;

    @Column(type = "VARCHAR(50)")
    private String name;

    @Column(type = "BIGINT")
    private Long userId;

    @Column(type = "DATETIME")
    private Date date;
}
