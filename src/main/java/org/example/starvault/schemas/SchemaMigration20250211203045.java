package org.example.starvault.schemas;

import stark.coderaider.fluentschema.commons.schemas.ColumnMetadata;
import stark.coderaider.fluentschema.commons.schemas.KeyMetadata;
import stark.coderaider.fluentschema.commons.schemas.SchemaMigrationBase;
import java.util.List;

public class SchemaMigration20250211203045 extends SchemaMigrationBase {
    @Override
    public void forward() {
        forwardBuilder.addColumn("user",
                ColumnMetadata.builder().name("avatar").type("VARCHAR(50)").nullable(true).unique(false).build());
    }
    @Override
    public void backward() {
        backwardBuilder.dropColumn("user", "avatar");
    }
}