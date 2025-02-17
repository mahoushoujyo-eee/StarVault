package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250212151632 extends SchemaMigrationBase {
    @Override
    public void forward() {
        forwardBuilder.alterColumn("user", "avatar",
                ColumnMetadata.builder().name("avatar").type("VARCHAR(500)").nullable(true).unique(false).build());
    }
    @Override
    public void backward() {
        backwardBuilder.alterColumn("user", "avatar",
                ColumnMetadata.builder().name("avatar").type("VARCHAR(50)").nullable(true).unique(false).build());
    }
}