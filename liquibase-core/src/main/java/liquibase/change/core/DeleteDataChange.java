package liquibase.change.core;

import liquibase.change.ChangeMetaData;
import liquibase.change.Param;
import liquibase.change.DatabaseChange;
import liquibase.database.Database;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.DeleteStatement;

@DatabaseChange(name="delete", description = "Deletes data from an existing table"
                , priority = ChangeMetaData.PRIORITY_DEFAULT, appliesTo = "table")
public class DeleteDataChange extends AbstractModifyDataChange {

    @Override
    public SqlStatement[] generateStatements(Database database) {

        DeleteStatement statement = new DeleteStatement(getCatalogName(), getSchemaName(), getTableName());

        statement.setWhere(where);

        for (Param whereParam : whereParams) {
            if (whereParam.getName() != null) {
                statement.addWhereColumnName(whereParam.getName());
            }
            statement.addWhereParameter(whereParam.getValueObject());
        }

        return new SqlStatement[]{
                statement
        };
    }

    @Override
    public String getConfirmationMessage() {
        return "Data deleted from " + getTableName();
    }



}
