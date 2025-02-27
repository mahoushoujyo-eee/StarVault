package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.SchemaSnapshotBase;
import java.util.List;

public class SchemaSnapshot extends SchemaSnapshotBase {
    @Override
    public void buildSchema() {
        schemaBuilder.table("directory", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("parent_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("file", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("url").type("VARCHAR(1000)").nullable(true).unique(false);
            builder.column().name("directory_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("historical_directory", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("directory_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("snapshot_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("parent_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("historical_file", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("file_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("snapshot_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("url").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("directory_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("user", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("username").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("password").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("email").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("avatar").type("VARCHAR(1000)").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        schemaBuilder.table("version", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("date").type("DATETIME").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
    }
}