package com.vrsa9208.design.patterns.book.factory.dao.adapter;

import java.sql.Connection;

public interface IDBAdapter {
	Connection getConnection();
}
