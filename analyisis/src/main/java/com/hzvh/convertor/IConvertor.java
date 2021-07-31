package com.hzvh.convertor;

import com.hzvh.kv.base.BaseDimension;

import java.sql.SQLException;

public interface IConvertor {

        int getDimensionID(BaseDimension baseDimension) throws SQLException;


}
