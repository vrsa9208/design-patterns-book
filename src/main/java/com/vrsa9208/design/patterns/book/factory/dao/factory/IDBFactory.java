package com.vrsa9208.design.patterns.book.factory.dao.factory;

import com.vrsa9208.design.patterns.book.factory.dao.adapter.IDBAdapter;
import com.vrsa9208.design.patterns.book.factory.values.DBType;

public interface IDBFactory {
	IDBAdapter getDbAdapter(DBType dbType);
	IDBAdapter getDefaultDbAdapter();
}
