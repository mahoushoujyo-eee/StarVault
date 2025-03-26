package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250326142619 extends SchemaMigrationBase {
    @Override
    public void forward() {
        forwardBuilder.addColumn("user",
                ColumnMetadata.builder().name("bucket").type("VARCHAR(50)").nullable(true).unique(false).build());
        forwardBuilder.addColumn("version",
                ColumnMetadata.builder().name("bucket").type("VARCHAR(50)").nullable(true).unique(false).build());
    }
    @Override
    public void backward() {
        backwardBuilder.dropColumn("user", "bucket");
        backwardBuilder.dropColumn("version", "bucket");
    }
}