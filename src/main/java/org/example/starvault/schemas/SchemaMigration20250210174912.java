package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250210174912 extends SchemaMigrationBase {
    @Override
    public void forward() {
        setInitialized(false);
        forwardBuilder.createTable("user", builder -> {
            builder.column().name("id").type("BIGINT").nullable(false).unique(false).autoIncrement(1);
            builder.column().name("username").type("VARCHAR(50)").nullable(true).unique(false);
            builder.column().name("password").type("VARCHAR(100)").nullable(true).unique(false);
            builder.column().name("email").type("VARCHAR(50)").nullable(true).unique(false);
            builder.primaryKey().columnName("id");
            builder.engine("InnoDB");
        });
    }
    @Override
    public void backward() {
        backwardBuilder.dropTable("user");
    }
}