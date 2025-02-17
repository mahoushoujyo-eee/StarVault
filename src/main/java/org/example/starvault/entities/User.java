package org.example.starvault.entities;

import lombok.Data;
import stark.coderaider.fluentschema.commons.NamingConvention;
import stark.coderaider.fluentschema.commons.annotations.AutoIncrement;
import stark.coderaider.fluentschema.commons.annotations.Column;
import stark.coderaider.fluentschema.commons.annotations.PrimaryKey;
import stark.coderaider.fluentschema.commons.annotations.Table;

@Table(namingConvention =  NamingConvention.LOWER_CASE_WITH_UNDERSCORE)
@Data
public class User
{
    @PrimaryKey
    @AutoIncrement
    private Long id;

    @Column(type = "VARCHAR(50)")
    private String username;

    @Column(type = "VARCHAR(100)")
    private String password;

    @Column(type = "VARCHAR(50)")
    private String email;

    @Column(type = "VARCHAR(1000)")
    private String avatar;
}
