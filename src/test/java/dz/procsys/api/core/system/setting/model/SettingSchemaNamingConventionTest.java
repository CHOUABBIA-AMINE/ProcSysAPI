package dz.procsys.api.core.system.setting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import dz.procsys.api.platform.kernel.model.GenericModel;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.junit.jupiter.api.Test;

class SettingSchemaNamingConventionTest {

    private static final List<Class<?>> ENTITY_CLASSES = List.of(
        ConstraintType.class,
        SettingValueType.class,
        SettingScopeType.class,
        SettingCategory.class,
        SettingDefinition.class,
        SettingConstraint.class,
        SettingValue.class
    );

    @Test
    void inheritedColumnsMustUseExpectedPhysicalNames() throws Exception {
        Field idField = GenericModel.class.getDeclaredField("id");
        Field versionField = GenericModel.class.getDeclaredField("version");

        Column idColumn = idField.getAnnotation(Column.class);
        Column versionColumn = versionField.getAnnotation(Column.class);

        assertEquals("F_00", idColumn.name());
        assertEquals("version", versionColumn.name());
    }

    @Test
    void tableConstraintsAndIndexesMustFollowEnterprisePrefixes() {
        for (Class<?> entityClass : ENTITY_CLASSES) {
            Table table = entityClass.getAnnotation(Table.class);
            assertTrue(table != null, () -> entityClass.getSimpleName() + " must declare @Table");

            for (UniqueConstraint uniqueConstraint : table.uniqueConstraints()) {
                assertTrue(uniqueConstraint.name().startsWith("UK_"),
                    () -> "Unique constraint must start with UK_: " + uniqueConstraint.name());
            }

            for (Index index : table.indexes()) {
                assertTrue(index.name().startsWith("IDX_"),
                    () -> "Index must start with IDX_: " + index.name());
            }
        }
    }

    @Test
    void foreignKeysMustFollowEnterprisePrefixes() {
        for (Class<?> entityClass : ENTITY_CLASSES) {
            for (Field field : entityClass.getDeclaredFields()) {
                JoinColumn joinColumn = field.getAnnotation(JoinColumn.class);
                if (joinColumn == null) {
                    continue;
                }
                ForeignKey foreignKey = joinColumn.foreignKey();
                assertTrue(foreignKey.name().startsWith("FK_"),
                    () -> "Foreign key must start with FK_: " + foreignKey.name());
            }
        }
    }
}
