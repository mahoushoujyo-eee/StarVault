package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250227212149 extends SchemaMigrationBase {
    @Override
    public void forward() {
        forwardBuilder.alterColumn("file", "url",
                ColumnMetadata.builder().name("url").type("VARCHAR(1000)").nullable(true).unique(false).build());
    }
    @Override
    public void backward() {
        backwardBuilder.alterColumn("file", "url",
                ColumnMetadata.builder().name("url").type("VARCHAR(100)").nullable(true).unique(false).build());
    }
}