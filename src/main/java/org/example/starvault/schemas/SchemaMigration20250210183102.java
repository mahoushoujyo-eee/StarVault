package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250210183102 extends SchemaMigrationBase {
    @Override
    public void forward() {
        forwardBuilder.createTable("file", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("url").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("directory_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        forwardBuilder.createTable("historical_file", builder -> {
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
        forwardBuilder.createTable("directory", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("parent_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        forwardBuilder.createTable("version", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("date").type("DATETIME").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
        forwardBuilder.createTable("historical_directory", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("directory_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("snapshot_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("name").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("parent_id").type("BIGINT").nullable(true).unique(false);
            builder.column().name("user_id").type("BIGINT").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
    }
    @Override
    public void backward() {
        backwardBuilder.dropTable("file");
        backwardBuilder.dropTable("historical_file");
        backwardBuilder.dropTable("directory");
        backwardBuilder.dropTable("version");
        backwardBuilder.dropTable("historical_directory");
    }
}