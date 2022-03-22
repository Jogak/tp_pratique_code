package org.sam.mines.address.persistence.config;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor;
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor;

import java.sql.Types;

public class CustomHsqlDialect extends HSQLDialect {

    public CustomHsqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "varchar(10000)");
    }

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        typeContributions.contributeType(new AbstractSingleColumnStandardBasicType<>(VarcharTypeDescriptor.INSTANCE, UUIDTypeDescriptor.INSTANCE) {
            @Override
            public String getName() {
                return "pg-uuid";
            }
        });
    }
}
