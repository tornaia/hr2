package hu.interconnect.util;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class MySQL5BitBooleanDialect extends MySQL5Dialect {
    
	public MySQL5BitBooleanDialect() {
        registerColumnType(Types.BOOLEAN, "bit" );     
    }
	
}
