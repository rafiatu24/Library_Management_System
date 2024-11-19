package com.rafiatu.library_management_system.models;

import com.rafiatu.library_management_system.config.Database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Model {
    protected Model() throws SQLException {
    }

    public abstract boolean save();
}
