parser grammar HSQLParser;
options{
    tokenVocab=HSQLLexer;
    output=AST;
}
tokens{
    LIST;
    SELECT_LIST;
}
@header{
package org.ict.hsql.parse;
}
sql_stat:
     create_stat
    |drop_stat
    |insert_stat
    |select_stat
    |delete_stat
    |update_stat
    |create_index
    ;
create_index
    :
    CREATE INDEX index_name ON table_name
    ->^(INDEX index_name table_name)
    ;
index_name
    :STR
    ;
update_stat
    :
    UPDATE table_name
    update_list
    where_clause?
    -> ^(UPDATE table_name update_list where_clause?)
    ;
update_list:
    SET^ update_element (COMMA! update_element)*
    ;
update_element:
    column EQ expression
    -> ^(EQ column expression)
    ;
expression
    :literals
    ;
delete_stat
    :
    DELETE  FROM  table_name where_clause?
    -> ^(DELETE table_name where_clause?)
    ;
where_clause
    :
    WHERE andCondition //主析取范式
    -> ^(WHERE andCondition)
    ;
andCondition
    :
    orCondition (AND^ orCondition)*
    ;
orCondition
    :
    atomCondition (OR^ atomCondition)*
    ;
atomCondition
    :
     column comp_op expression -> ^(comp_op column expression)
    | L_PAREN andCondition R_PAREN
    //| NOT condition  -> ^(NOT condition)
    ;
comp_op
    :
    EQ
    |LT
    |LTE
    |GT
    |GTE
    ;
select_stat:
    SELECT s=select_list FROM tn=table_name w=where_clause?
    ->^(SELECT select_list table_name where_clause?)
    ;
select_list:
    projector (COMMA projector)*
    -> ^(SELECT_LIST projector+)
    ;
projector:
    column
    |fun L_PAREN column R_PAREN ->^(fun column)
    ;
fun:
    |SUM
    ;
insert_stat:
    INSERT INTO tn=table_name VALUES L_PAREN vl=literal_list R_PAREN
    ->^(INSERT $tn $vl)
    ;
drop_stat:
    DROP TABLE tn=table_name
    -> ^(DROP $tn)
    ;
create_stat
    :CREATE TABLE table_name L_PAREN column_withtype_list R_PAREN
     -> ^(CREATE table_name column_withtype_list)
    ;
table_name
    :STR
    ;
column_withtype_list
    :column_withtype (COMMA column_withtype)*
    ;
column_withtype
    :column type
    ;
column
    :
    STR
    ;
attr:
    PRIMARYKEY
    ;
type
    :INT
    |STRING
    |DOUBLE
    |LONG
    ;
literals

    :MINUS?(INTEGER_LITERAL
    |LONG_LITERAL
    |DOUBLE_LITERAL
    )
    |STRING_LITERAL
    ;
literal_list
    :literals (COMMA literals)*
    ;