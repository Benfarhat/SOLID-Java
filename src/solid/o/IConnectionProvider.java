package solid.o;

import java.sql.Connection;

public interface IConnectionProvider {
    public Connection establisheConnection();
}
