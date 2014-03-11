// $ANTLR 3.5 HSQLParser.g 2014-02-24 14:40:00

package org.ict.hsql.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class HSQLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "CHAR", "CHAR_ESC", "COMMA", 
		"CREATE", "DBL_QUOTE_CHAR", "DECIMAL", "DELETE", "DIGIT", "DOT", "DOUBLE", 
		"DOUBLE_LITERAL", "DROP", "EOL", "EQ", "FROM", "GT", "GTE", "INDEX", "INSERT", 
		"INT", "INTEGER_LITERAL", "INTO", "LETTER", "LONG", "LONG_LITERAL", "LT", 
		"LTE", "L_PAREN", "MINUS", "NEQ", "ON", "OR", "POSINTEGER", "PRIMARYKEY", 
		"R_PAREN", "SELECT", "SET", "STR", "STRING", "STRING_LITERAL", "STR_LITERALS", 
		"SUM", "TABLE", "UPDATE", "VALUES", "WHERE", "WS", "LIST", "SELECT_LIST"
	};
	public static final int EOF=-1;
	public static final int AND=4;
	public static final int CHAR=5;
	public static final int CHAR_ESC=6;
	public static final int COMMA=7;
	public static final int CREATE=8;
	public static final int DBL_QUOTE_CHAR=9;
	public static final int DECIMAL=10;
	public static final int DELETE=11;
	public static final int DIGIT=12;
	public static final int DOT=13;
	public static final int DOUBLE=14;
	public static final int DOUBLE_LITERAL=15;
	public static final int DROP=16;
	public static final int EOL=17;
	public static final int EQ=18;
	public static final int FROM=19;
	public static final int GT=20;
	public static final int GTE=21;
	public static final int INDEX=22;
	public static final int INSERT=23;
	public static final int INT=24;
	public static final int INTEGER_LITERAL=25;
	public static final int INTO=26;
	public static final int LETTER=27;
	public static final int LONG=28;
	public static final int LONG_LITERAL=29;
	public static final int LT=30;
	public static final int LTE=31;
	public static final int L_PAREN=32;
	public static final int MINUS=33;
	public static final int NEQ=34;
	public static final int ON=35;
	public static final int OR=36;
	public static final int POSINTEGER=37;
	public static final int PRIMARYKEY=38;
	public static final int R_PAREN=39;
	public static final int SELECT=40;
	public static final int SET=41;
	public static final int STR=42;
	public static final int STRING=43;
	public static final int STRING_LITERAL=44;
	public static final int STR_LITERALS=45;
	public static final int SUM=46;
	public static final int TABLE=47;
	public static final int UPDATE=48;
	public static final int VALUES=49;
	public static final int WHERE=50;
	public static final int WS=51;
	public static final int LIST=52;
	public static final int SELECT_LIST=53;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public HSQLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public HSQLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return HSQLParser.tokenNames; }
	@Override public String getGrammarFileName() { return "HSQLParser.g"; }


	public static class sql_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "sql_stat"
	// HSQLParser.g:13:1: sql_stat : ( create_stat | drop_stat | insert_stat | select_stat | delete_stat | update_stat | create_index );
	public final HSQLParser.sql_stat_return sql_stat() throws RecognitionException {
		HSQLParser.sql_stat_return retval = new HSQLParser.sql_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope create_stat1 =null;
		ParserRuleReturnScope drop_stat2 =null;
		ParserRuleReturnScope insert_stat3 =null;
		ParserRuleReturnScope select_stat4 =null;
		ParserRuleReturnScope delete_stat5 =null;
		ParserRuleReturnScope update_stat6 =null;
		ParserRuleReturnScope create_index7 =null;


		try {
			// HSQLParser.g:13:9: ( create_stat | drop_stat | insert_stat | select_stat | delete_stat | update_stat | create_index )
			int alt1=7;
			switch ( input.LA(1) ) {
			case CREATE:
				{
				int LA1_1 = input.LA(2);
				if ( (LA1_1==TABLE) ) {
					alt1=1;
				}
				else if ( (LA1_1==INDEX) ) {
					alt1=7;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DROP:
				{
				alt1=2;
				}
				break;
			case INSERT:
				{
				alt1=3;
				}
				break;
			case SELECT:
				{
				alt1=4;
				}
				break;
			case DELETE:
				{
				alt1=5;
				}
				break;
			case UPDATE:
				{
				alt1=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// HSQLParser.g:14:6: create_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_create_stat_in_sql_stat59);
					create_stat1=create_stat();
					state._fsp--;

					adaptor.addChild(root_0, create_stat1.getTree());

					}
					break;
				case 2 :
					// HSQLParser.g:15:6: drop_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_drop_stat_in_sql_stat66);
					drop_stat2=drop_stat();
					state._fsp--;

					adaptor.addChild(root_0, drop_stat2.getTree());

					}
					break;
				case 3 :
					// HSQLParser.g:16:6: insert_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_insert_stat_in_sql_stat73);
					insert_stat3=insert_stat();
					state._fsp--;

					adaptor.addChild(root_0, insert_stat3.getTree());

					}
					break;
				case 4 :
					// HSQLParser.g:17:6: select_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_select_stat_in_sql_stat80);
					select_stat4=select_stat();
					state._fsp--;

					adaptor.addChild(root_0, select_stat4.getTree());

					}
					break;
				case 5 :
					// HSQLParser.g:18:6: delete_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_delete_stat_in_sql_stat87);
					delete_stat5=delete_stat();
					state._fsp--;

					adaptor.addChild(root_0, delete_stat5.getTree());

					}
					break;
				case 6 :
					// HSQLParser.g:19:6: update_stat
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_update_stat_in_sql_stat94);
					update_stat6=update_stat();
					state._fsp--;

					adaptor.addChild(root_0, update_stat6.getTree());

					}
					break;
				case 7 :
					// HSQLParser.g:20:6: create_index
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_create_index_in_sql_stat101);
					create_index7=create_index();
					state._fsp--;

					adaptor.addChild(root_0, create_index7.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sql_stat"


	public static class create_index_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "create_index"
	// HSQLParser.g:22:1: create_index : CREATE INDEX index_name ON table_name -> ^( INDEX index_name table_name ) ;
	public final HSQLParser.create_index_return create_index() throws RecognitionException {
		HSQLParser.create_index_return retval = new HSQLParser.create_index_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token CREATE8=null;
		Token INDEX9=null;
		Token ON11=null;
		ParserRuleReturnScope index_name10 =null;
		ParserRuleReturnScope table_name12 =null;

		Object CREATE8_tree=null;
		Object INDEX9_tree=null;
		Object ON11_tree=null;
		RewriteRuleTokenStream stream_INDEX=new RewriteRuleTokenStream(adaptor,"token INDEX");
		RewriteRuleTokenStream stream_ON=new RewriteRuleTokenStream(adaptor,"token ON");
		RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
		RewriteRuleSubtreeStream stream_index_name=new RewriteRuleSubtreeStream(adaptor,"rule index_name");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:23:5: ( CREATE INDEX index_name ON table_name -> ^( INDEX index_name table_name ) )
			// HSQLParser.g:24:5: CREATE INDEX index_name ON table_name
			{
			CREATE8=(Token)match(input,CREATE,FOLLOW_CREATE_in_create_index121);  
			stream_CREATE.add(CREATE8);

			INDEX9=(Token)match(input,INDEX,FOLLOW_INDEX_in_create_index123);  
			stream_INDEX.add(INDEX9);

			pushFollow(FOLLOW_index_name_in_create_index125);
			index_name10=index_name();
			state._fsp--;

			stream_index_name.add(index_name10.getTree());
			ON11=(Token)match(input,ON,FOLLOW_ON_in_create_index127);  
			stream_ON.add(ON11);

			pushFollow(FOLLOW_table_name_in_create_index129);
			table_name12=table_name();
			state._fsp--;

			stream_table_name.add(table_name12.getTree());
			// AST REWRITE
			// elements: table_name, index_name, INDEX
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 25:5: -> ^( INDEX index_name table_name )
			{
				// HSQLParser.g:25:7: ^( INDEX index_name table_name )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_INDEX.nextNode(), root_1);
				adaptor.addChild(root_1, stream_index_name.nextTree());
				adaptor.addChild(root_1, stream_table_name.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "create_index"


	public static class index_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "index_name"
	// HSQLParser.g:27:1: index_name : STR ;
	public final HSQLParser.index_name_return index_name() throws RecognitionException {
		HSQLParser.index_name_return retval = new HSQLParser.index_name_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STR13=null;

		Object STR13_tree=null;

		try {
			// HSQLParser.g:28:5: ( STR )
			// HSQLParser.g:28:6: STR
			{
			root_0 = (Object)adaptor.nil();


			STR13=(Token)match(input,STR,FOLLOW_STR_in_index_name157); 
			STR13_tree = (Object)adaptor.create(STR13);
			adaptor.addChild(root_0, STR13_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "index_name"


	public static class update_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "update_stat"
	// HSQLParser.g:30:1: update_stat : UPDATE table_name update_list ( where_clause )? -> ^( UPDATE table_name update_list ( where_clause )? ) ;
	public final HSQLParser.update_stat_return update_stat() throws RecognitionException {
		HSQLParser.update_stat_return retval = new HSQLParser.update_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token UPDATE14=null;
		ParserRuleReturnScope table_name15 =null;
		ParserRuleReturnScope update_list16 =null;
		ParserRuleReturnScope where_clause17 =null;

		Object UPDATE14_tree=null;
		RewriteRuleTokenStream stream_UPDATE=new RewriteRuleTokenStream(adaptor,"token UPDATE");
		RewriteRuleSubtreeStream stream_update_list=new RewriteRuleSubtreeStream(adaptor,"rule update_list");
		RewriteRuleSubtreeStream stream_where_clause=new RewriteRuleSubtreeStream(adaptor,"rule where_clause");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:31:5: ( UPDATE table_name update_list ( where_clause )? -> ^( UPDATE table_name update_list ( where_clause )? ) )
			// HSQLParser.g:32:5: UPDATE table_name update_list ( where_clause )?
			{
			UPDATE14=(Token)match(input,UPDATE,FOLLOW_UPDATE_in_update_stat177);  
			stream_UPDATE.add(UPDATE14);

			pushFollow(FOLLOW_table_name_in_update_stat179);
			table_name15=table_name();
			state._fsp--;

			stream_table_name.add(table_name15.getTree());
			pushFollow(FOLLOW_update_list_in_update_stat185);
			update_list16=update_list();
			state._fsp--;

			stream_update_list.add(update_list16.getTree());
			// HSQLParser.g:34:5: ( where_clause )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==WHERE) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// HSQLParser.g:34:5: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_update_stat191);
					where_clause17=where_clause();
					state._fsp--;

					stream_where_clause.add(where_clause17.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: update_list, where_clause, table_name, UPDATE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 35:5: -> ^( UPDATE table_name update_list ( where_clause )? )
			{
				// HSQLParser.g:35:8: ^( UPDATE table_name update_list ( where_clause )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_UPDATE.nextNode(), root_1);
				adaptor.addChild(root_1, stream_table_name.nextTree());
				adaptor.addChild(root_1, stream_update_list.nextTree());
				// HSQLParser.g:35:40: ( where_clause )?
				if ( stream_where_clause.hasNext() ) {
					adaptor.addChild(root_1, stream_where_clause.nextTree());
				}
				stream_where_clause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_stat"


	public static class update_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "update_list"
	// HSQLParser.g:37:1: update_list : SET ^ update_element ( COMMA ! update_element )* ;
	public final HSQLParser.update_list_return update_list() throws RecognitionException {
		HSQLParser.update_list_return retval = new HSQLParser.update_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SET18=null;
		Token COMMA20=null;
		ParserRuleReturnScope update_element19 =null;
		ParserRuleReturnScope update_element21 =null;

		Object SET18_tree=null;
		Object COMMA20_tree=null;

		try {
			// HSQLParser.g:37:12: ( SET ^ update_element ( COMMA ! update_element )* )
			// HSQLParser.g:38:5: SET ^ update_element ( COMMA ! update_element )*
			{
			root_0 = (Object)adaptor.nil();


			SET18=(Token)match(input,SET,FOLLOW_SET_in_update_list224); 
			SET18_tree = (Object)adaptor.create(SET18);
			root_0 = (Object)adaptor.becomeRoot(SET18_tree, root_0);

			pushFollow(FOLLOW_update_element_in_update_list227);
			update_element19=update_element();
			state._fsp--;

			adaptor.addChild(root_0, update_element19.getTree());

			// HSQLParser.g:38:25: ( COMMA ! update_element )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==COMMA) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// HSQLParser.g:38:26: COMMA ! update_element
					{
					COMMA20=(Token)match(input,COMMA,FOLLOW_COMMA_in_update_list230); 
					pushFollow(FOLLOW_update_element_in_update_list233);
					update_element21=update_element();
					state._fsp--;

					adaptor.addChild(root_0, update_element21.getTree());

					}
					break;

				default :
					break loop3;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_list"


	public static class update_element_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "update_element"
	// HSQLParser.g:40:1: update_element : column EQ expression -> ^( EQ column expression ) ;
	public final HSQLParser.update_element_return update_element() throws RecognitionException {
		HSQLParser.update_element_return retval = new HSQLParser.update_element_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token EQ23=null;
		ParserRuleReturnScope column22 =null;
		ParserRuleReturnScope expression24 =null;

		Object EQ23_tree=null;
		RewriteRuleTokenStream stream_EQ=new RewriteRuleTokenStream(adaptor,"token EQ");
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_column=new RewriteRuleSubtreeStream(adaptor,"rule column");

		try {
			// HSQLParser.g:40:15: ( column EQ expression -> ^( EQ column expression ) )
			// HSQLParser.g:41:5: column EQ expression
			{
			pushFollow(FOLLOW_column_in_update_element250);
			column22=column();
			state._fsp--;

			stream_column.add(column22.getTree());
			EQ23=(Token)match(input,EQ,FOLLOW_EQ_in_update_element252);  
			stream_EQ.add(EQ23);

			pushFollow(FOLLOW_expression_in_update_element254);
			expression24=expression();
			state._fsp--;

			stream_expression.add(expression24.getTree());
			// AST REWRITE
			// elements: column, EQ, expression
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 42:5: -> ^( EQ column expression )
			{
				// HSQLParser.g:42:8: ^( EQ column expression )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_EQ.nextNode(), root_1);
				adaptor.addChild(root_1, stream_column.nextTree());
				adaptor.addChild(root_1, stream_expression.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_element"


	public static class expression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// HSQLParser.g:44:1: expression : literals ;
	public final HSQLParser.expression_return expression() throws RecognitionException {
		HSQLParser.expression_return retval = new HSQLParser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope literals25 =null;


		try {
			// HSQLParser.g:45:5: ( literals )
			// HSQLParser.g:45:6: literals
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_literals_in_expression283);
			literals25=literals();
			state._fsp--;

			adaptor.addChild(root_0, literals25.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class delete_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "delete_stat"
	// HSQLParser.g:47:1: delete_stat : DELETE FROM table_name ( where_clause )? -> ^( DELETE table_name ( where_clause )? ) ;
	public final HSQLParser.delete_stat_return delete_stat() throws RecognitionException {
		HSQLParser.delete_stat_return retval = new HSQLParser.delete_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DELETE26=null;
		Token FROM27=null;
		ParserRuleReturnScope table_name28 =null;
		ParserRuleReturnScope where_clause29 =null;

		Object DELETE26_tree=null;
		Object FROM27_tree=null;
		RewriteRuleTokenStream stream_DELETE=new RewriteRuleTokenStream(adaptor,"token DELETE");
		RewriteRuleTokenStream stream_FROM=new RewriteRuleTokenStream(adaptor,"token FROM");
		RewriteRuleSubtreeStream stream_where_clause=new RewriteRuleSubtreeStream(adaptor,"rule where_clause");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:48:5: ( DELETE FROM table_name ( where_clause )? -> ^( DELETE table_name ( where_clause )? ) )
			// HSQLParser.g:49:5: DELETE FROM table_name ( where_clause )?
			{
			DELETE26=(Token)match(input,DELETE,FOLLOW_DELETE_in_delete_stat303);  
			stream_DELETE.add(DELETE26);

			FROM27=(Token)match(input,FROM,FOLLOW_FROM_in_delete_stat306);  
			stream_FROM.add(FROM27);

			pushFollow(FOLLOW_table_name_in_delete_stat309);
			table_name28=table_name();
			state._fsp--;

			stream_table_name.add(table_name28.getTree());
			// HSQLParser.g:49:30: ( where_clause )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==WHERE) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// HSQLParser.g:49:30: where_clause
					{
					pushFollow(FOLLOW_where_clause_in_delete_stat311);
					where_clause29=where_clause();
					state._fsp--;

					stream_where_clause.add(where_clause29.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: where_clause, table_name, DELETE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 50:5: -> ^( DELETE table_name ( where_clause )? )
			{
				// HSQLParser.g:50:8: ^( DELETE table_name ( where_clause )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_DELETE.nextNode(), root_1);
				adaptor.addChild(root_1, stream_table_name.nextTree());
				// HSQLParser.g:50:28: ( where_clause )?
				if ( stream_where_clause.hasNext() ) {
					adaptor.addChild(root_1, stream_where_clause.nextTree());
				}
				stream_where_clause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete_stat"


	public static class where_clause_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "where_clause"
	// HSQLParser.g:52:1: where_clause : WHERE andCondition -> ^( WHERE andCondition ) ;
	public final HSQLParser.where_clause_return where_clause() throws RecognitionException {
		HSQLParser.where_clause_return retval = new HSQLParser.where_clause_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token WHERE30=null;
		ParserRuleReturnScope andCondition31 =null;

		Object WHERE30_tree=null;
		RewriteRuleTokenStream stream_WHERE=new RewriteRuleTokenStream(adaptor,"token WHERE");
		RewriteRuleSubtreeStream stream_andCondition=new RewriteRuleSubtreeStream(adaptor,"rule andCondition");

		try {
			// HSQLParser.g:53:5: ( WHERE andCondition -> ^( WHERE andCondition ) )
			// HSQLParser.g:54:5: WHERE andCondition
			{
			WHERE30=(Token)match(input,WHERE,FOLLOW_WHERE_in_where_clause347);  
			stream_WHERE.add(WHERE30);

			pushFollow(FOLLOW_andCondition_in_where_clause349);
			andCondition31=andCondition();
			state._fsp--;

			stream_andCondition.add(andCondition31.getTree());
			// AST REWRITE
			// elements: andCondition, WHERE
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 55:5: -> ^( WHERE andCondition )
			{
				// HSQLParser.g:55:8: ^( WHERE andCondition )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_WHERE.nextNode(), root_1);
				adaptor.addChild(root_1, stream_andCondition.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "where_clause"


	public static class andCondition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "andCondition"
	// HSQLParser.g:57:1: andCondition : orCondition ( AND ^ orCondition )* ;
	public final HSQLParser.andCondition_return andCondition() throws RecognitionException {
		HSQLParser.andCondition_return retval = new HSQLParser.andCondition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token AND33=null;
		ParserRuleReturnScope orCondition32 =null;
		ParserRuleReturnScope orCondition34 =null;

		Object AND33_tree=null;

		try {
			// HSQLParser.g:58:5: ( orCondition ( AND ^ orCondition )* )
			// HSQLParser.g:59:5: orCondition ( AND ^ orCondition )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_orCondition_in_andCondition382);
			orCondition32=orCondition();
			state._fsp--;

			adaptor.addChild(root_0, orCondition32.getTree());

			// HSQLParser.g:59:17: ( AND ^ orCondition )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==AND) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// HSQLParser.g:59:18: AND ^ orCondition
					{
					AND33=(Token)match(input,AND,FOLLOW_AND_in_andCondition385); 
					AND33_tree = (Object)adaptor.create(AND33);
					root_0 = (Object)adaptor.becomeRoot(AND33_tree, root_0);

					pushFollow(FOLLOW_orCondition_in_andCondition388);
					orCondition34=orCondition();
					state._fsp--;

					adaptor.addChild(root_0, orCondition34.getTree());

					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "andCondition"


	public static class orCondition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "orCondition"
	// HSQLParser.g:61:1: orCondition : atomCondition ( OR ^ atomCondition )* ;
	public final HSQLParser.orCondition_return orCondition() throws RecognitionException {
		HSQLParser.orCondition_return retval = new HSQLParser.orCondition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token OR36=null;
		ParserRuleReturnScope atomCondition35 =null;
		ParserRuleReturnScope atomCondition37 =null;

		Object OR36_tree=null;

		try {
			// HSQLParser.g:62:5: ( atomCondition ( OR ^ atomCondition )* )
			// HSQLParser.g:63:5: atomCondition ( OR ^ atomCondition )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atomCondition_in_orCondition410);
			atomCondition35=atomCondition();
			state._fsp--;

			adaptor.addChild(root_0, atomCondition35.getTree());

			// HSQLParser.g:63:19: ( OR ^ atomCondition )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==OR) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// HSQLParser.g:63:20: OR ^ atomCondition
					{
					OR36=(Token)match(input,OR,FOLLOW_OR_in_orCondition413); 
					OR36_tree = (Object)adaptor.create(OR36);
					root_0 = (Object)adaptor.becomeRoot(OR36_tree, root_0);

					pushFollow(FOLLOW_atomCondition_in_orCondition416);
					atomCondition37=atomCondition();
					state._fsp--;

					adaptor.addChild(root_0, atomCondition37.getTree());

					}
					break;

				default :
					break loop6;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orCondition"


	public static class atomCondition_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atomCondition"
	// HSQLParser.g:65:1: atomCondition : ( column comp_op expression -> ^( comp_op column expression ) | L_PAREN andCondition R_PAREN );
	public final HSQLParser.atomCondition_return atomCondition() throws RecognitionException {
		HSQLParser.atomCondition_return retval = new HSQLParser.atomCondition_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token L_PAREN41=null;
		Token R_PAREN43=null;
		ParserRuleReturnScope column38 =null;
		ParserRuleReturnScope comp_op39 =null;
		ParserRuleReturnScope expression40 =null;
		ParserRuleReturnScope andCondition42 =null;

		Object L_PAREN41_tree=null;
		Object R_PAREN43_tree=null;
		RewriteRuleSubtreeStream stream_expression=new RewriteRuleSubtreeStream(adaptor,"rule expression");
		RewriteRuleSubtreeStream stream_column=new RewriteRuleSubtreeStream(adaptor,"rule column");
		RewriteRuleSubtreeStream stream_comp_op=new RewriteRuleSubtreeStream(adaptor,"rule comp_op");

		try {
			// HSQLParser.g:66:5: ( column comp_op expression -> ^( comp_op column expression ) | L_PAREN andCondition R_PAREN )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==STR) ) {
				alt7=1;
			}
			else if ( (LA7_0==L_PAREN) ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// HSQLParser.g:67:6: column comp_op expression
					{
					pushFollow(FOLLOW_column_in_atomCondition439);
					column38=column();
					state._fsp--;

					stream_column.add(column38.getTree());
					pushFollow(FOLLOW_comp_op_in_atomCondition441);
					comp_op39=comp_op();
					state._fsp--;

					stream_comp_op.add(comp_op39.getTree());
					pushFollow(FOLLOW_expression_in_atomCondition443);
					expression40=expression();
					state._fsp--;

					stream_expression.add(expression40.getTree());
					// AST REWRITE
					// elements: column, comp_op, expression
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 67:32: -> ^( comp_op column expression )
					{
						// HSQLParser.g:67:35: ^( comp_op column expression )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_comp_op.nextNode(), root_1);
						adaptor.addChild(root_1, stream_column.nextTree());
						adaptor.addChild(root_1, stream_expression.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;
				case 2 :
					// HSQLParser.g:68:7: L_PAREN andCondition R_PAREN
					{
					root_0 = (Object)adaptor.nil();


					L_PAREN41=(Token)match(input,L_PAREN,FOLLOW_L_PAREN_in_atomCondition461); 
					L_PAREN41_tree = (Object)adaptor.create(L_PAREN41);
					adaptor.addChild(root_0, L_PAREN41_tree);

					pushFollow(FOLLOW_andCondition_in_atomCondition463);
					andCondition42=andCondition();
					state._fsp--;

					adaptor.addChild(root_0, andCondition42.getTree());

					R_PAREN43=(Token)match(input,R_PAREN,FOLLOW_R_PAREN_in_atomCondition465); 
					R_PAREN43_tree = (Object)adaptor.create(R_PAREN43);
					adaptor.addChild(root_0, R_PAREN43_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atomCondition"


	public static class comp_op_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "comp_op"
	// HSQLParser.g:71:1: comp_op : ( EQ | LT | LTE | GT | GTE );
	public final HSQLParser.comp_op_return comp_op() throws RecognitionException {
		HSQLParser.comp_op_return retval = new HSQLParser.comp_op_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set44=null;

		Object set44_tree=null;

		try {
			// HSQLParser.g:72:5: ( EQ | LT | LTE | GT | GTE )
			// HSQLParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set44=input.LT(1);
			if ( input.LA(1)==EQ||(input.LA(1) >= GT && input.LA(1) <= GTE)||(input.LA(1) >= LT && input.LA(1) <= LTE) ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set44));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_op"


	public static class select_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select_stat"
	// HSQLParser.g:79:1: select_stat : SELECT s= select_list FROM tn= table_name (w= where_clause )? -> ^( SELECT select_list table_name ( where_clause )? ) ;
	public final HSQLParser.select_stat_return select_stat() throws RecognitionException {
		HSQLParser.select_stat_return retval = new HSQLParser.select_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SELECT45=null;
		Token FROM46=null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope tn =null;
		ParserRuleReturnScope w =null;

		Object SELECT45_tree=null;
		Object FROM46_tree=null;
		RewriteRuleTokenStream stream_FROM=new RewriteRuleTokenStream(adaptor,"token FROM");
		RewriteRuleTokenStream stream_SELECT=new RewriteRuleTokenStream(adaptor,"token SELECT");
		RewriteRuleSubtreeStream stream_select_list=new RewriteRuleSubtreeStream(adaptor,"rule select_list");
		RewriteRuleSubtreeStream stream_where_clause=new RewriteRuleSubtreeStream(adaptor,"rule where_clause");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:79:12: ( SELECT s= select_list FROM tn= table_name (w= where_clause )? -> ^( SELECT select_list table_name ( where_clause )? ) )
			// HSQLParser.g:80:5: SELECT s= select_list FROM tn= table_name (w= where_clause )?
			{
			SELECT45=(Token)match(input,SELECT,FOLLOW_SELECT_in_select_stat533);  
			stream_SELECT.add(SELECT45);

			pushFollow(FOLLOW_select_list_in_select_stat537);
			s=select_list();
			state._fsp--;

			stream_select_list.add(s.getTree());
			FROM46=(Token)match(input,FROM,FOLLOW_FROM_in_select_stat539);  
			stream_FROM.add(FROM46);

			pushFollow(FOLLOW_table_name_in_select_stat543);
			tn=table_name();
			state._fsp--;

			stream_table_name.add(tn.getTree());
			// HSQLParser.g:80:46: (w= where_clause )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==WHERE) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// HSQLParser.g:80:46: w= where_clause
					{
					pushFollow(FOLLOW_where_clause_in_select_stat547);
					w=where_clause();
					state._fsp--;

					stream_where_clause.add(w.getTree());
					}
					break;

			}

			// AST REWRITE
			// elements: table_name, where_clause, select_list, SELECT
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 81:5: -> ^( SELECT select_list table_name ( where_clause )? )
			{
				// HSQLParser.g:81:7: ^( SELECT select_list table_name ( where_clause )? )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_SELECT.nextNode(), root_1);
				adaptor.addChild(root_1, stream_select_list.nextTree());
				adaptor.addChild(root_1, stream_table_name.nextTree());
				// HSQLParser.g:81:39: ( where_clause )?
				if ( stream_where_clause.hasNext() ) {
					adaptor.addChild(root_1, stream_where_clause.nextTree());
				}
				stream_where_clause.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_stat"


	public static class select_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "select_list"
	// HSQLParser.g:83:1: select_list : projector ( COMMA projector )* -> ^( SELECT_LIST ( projector )+ ) ;
	public final HSQLParser.select_list_return select_list() throws RecognitionException {
		HSQLParser.select_list_return retval = new HSQLParser.select_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA48=null;
		ParserRuleReturnScope projector47 =null;
		ParserRuleReturnScope projector49 =null;

		Object COMMA48_tree=null;
		RewriteRuleTokenStream stream_COMMA=new RewriteRuleTokenStream(adaptor,"token COMMA");
		RewriteRuleSubtreeStream stream_projector=new RewriteRuleSubtreeStream(adaptor,"rule projector");

		try {
			// HSQLParser.g:83:12: ( projector ( COMMA projector )* -> ^( SELECT_LIST ( projector )+ ) )
			// HSQLParser.g:84:5: projector ( COMMA projector )*
			{
			pushFollow(FOLLOW_projector_in_select_list579);
			projector47=projector();
			state._fsp--;

			stream_projector.add(projector47.getTree());
			// HSQLParser.g:84:15: ( COMMA projector )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==COMMA) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// HSQLParser.g:84:16: COMMA projector
					{
					COMMA48=(Token)match(input,COMMA,FOLLOW_COMMA_in_select_list582);  
					stream_COMMA.add(COMMA48);

					pushFollow(FOLLOW_projector_in_select_list584);
					projector49=projector();
					state._fsp--;

					stream_projector.add(projector49.getTree());
					}
					break;

				default :
					break loop9;
				}
			}

			// AST REWRITE
			// elements: projector
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 85:5: -> ^( SELECT_LIST ( projector )+ )
			{
				// HSQLParser.g:85:8: ^( SELECT_LIST ( projector )+ )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot((Object)adaptor.create(SELECT_LIST, "SELECT_LIST"), root_1);
				if ( !(stream_projector.hasNext()) ) {
					throw new RewriteEarlyExitException();
				}
				while ( stream_projector.hasNext() ) {
					adaptor.addChild(root_1, stream_projector.nextTree());
				}
				stream_projector.reset();

				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_list"


	public static class projector_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "projector"
	// HSQLParser.g:87:1: projector : ( column | fun L_PAREN column R_PAREN -> ^( fun column ) );
	public final HSQLParser.projector_return projector() throws RecognitionException {
		HSQLParser.projector_return retval = new HSQLParser.projector_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token L_PAREN52=null;
		Token R_PAREN54=null;
		ParserRuleReturnScope column50 =null;
		ParserRuleReturnScope fun51 =null;
		ParserRuleReturnScope column53 =null;

		Object L_PAREN52_tree=null;
		Object R_PAREN54_tree=null;
		RewriteRuleTokenStream stream_R_PAREN=new RewriteRuleTokenStream(adaptor,"token R_PAREN");
		RewriteRuleTokenStream stream_L_PAREN=new RewriteRuleTokenStream(adaptor,"token L_PAREN");
		RewriteRuleSubtreeStream stream_column=new RewriteRuleSubtreeStream(adaptor,"rule column");
		RewriteRuleSubtreeStream stream_fun=new RewriteRuleSubtreeStream(adaptor,"rule fun");

		try {
			// HSQLParser.g:87:10: ( column | fun L_PAREN column R_PAREN -> ^( fun column ) )
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==STR) ) {
				alt10=1;
			}
			else if ( (LA10_0==L_PAREN||LA10_0==SUM) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// HSQLParser.g:88:5: column
					{
					root_0 = (Object)adaptor.nil();


					pushFollow(FOLLOW_column_in_projector614);
					column50=column();
					state._fsp--;

					adaptor.addChild(root_0, column50.getTree());

					}
					break;
				case 2 :
					// HSQLParser.g:89:6: fun L_PAREN column R_PAREN
					{
					pushFollow(FOLLOW_fun_in_projector621);
					fun51=fun();
					state._fsp--;

					stream_fun.add(fun51.getTree());
					L_PAREN52=(Token)match(input,L_PAREN,FOLLOW_L_PAREN_in_projector623);  
					stream_L_PAREN.add(L_PAREN52);

					pushFollow(FOLLOW_column_in_projector625);
					column53=column();
					state._fsp--;

					stream_column.add(column53.getTree());
					R_PAREN54=(Token)match(input,R_PAREN,FOLLOW_R_PAREN_in_projector627);  
					stream_R_PAREN.add(R_PAREN54);

					// AST REWRITE
					// elements: fun, column
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (Object)adaptor.nil();
					// 89:33: -> ^( fun column )
					{
						// HSQLParser.g:89:35: ^( fun column )
						{
						Object root_1 = (Object)adaptor.nil();
						root_1 = (Object)adaptor.becomeRoot(stream_fun.nextNode(), root_1);
						adaptor.addChild(root_1, stream_column.nextTree());
						adaptor.addChild(root_0, root_1);
						}

					}


					retval.tree = root_0;

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "projector"


	public static class fun_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "fun"
	// HSQLParser.g:91:1: fun : (| SUM );
	public final HSQLParser.fun_return fun() throws RecognitionException {
		HSQLParser.fun_return retval = new HSQLParser.fun_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token SUM55=null;

		Object SUM55_tree=null;

		try {
			// HSQLParser.g:91:4: (| SUM )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==L_PAREN) ) {
				alt11=1;
			}
			else if ( (LA11_0==SUM) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// HSQLParser.g:92:5: 
					{
					root_0 = (Object)adaptor.nil();


					}
					break;
				case 2 :
					// HSQLParser.g:92:6: SUM
					{
					root_0 = (Object)adaptor.nil();


					SUM55=(Token)match(input,SUM,FOLLOW_SUM_in_fun650); 
					SUM55_tree = (Object)adaptor.create(SUM55);
					adaptor.addChild(root_0, SUM55_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fun"


	public static class insert_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "insert_stat"
	// HSQLParser.g:94:1: insert_stat : INSERT INTO tn= table_name VALUES L_PAREN vl= literal_list R_PAREN -> ^( INSERT $tn $vl) ;
	public final HSQLParser.insert_stat_return insert_stat() throws RecognitionException {
		HSQLParser.insert_stat_return retval = new HSQLParser.insert_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token INSERT56=null;
		Token INTO57=null;
		Token VALUES58=null;
		Token L_PAREN59=null;
		Token R_PAREN60=null;
		ParserRuleReturnScope tn =null;
		ParserRuleReturnScope vl =null;

		Object INSERT56_tree=null;
		Object INTO57_tree=null;
		Object VALUES58_tree=null;
		Object L_PAREN59_tree=null;
		Object R_PAREN60_tree=null;
		RewriteRuleTokenStream stream_INSERT=new RewriteRuleTokenStream(adaptor,"token INSERT");
		RewriteRuleTokenStream stream_INTO=new RewriteRuleTokenStream(adaptor,"token INTO");
		RewriteRuleTokenStream stream_R_PAREN=new RewriteRuleTokenStream(adaptor,"token R_PAREN");
		RewriteRuleTokenStream stream_L_PAREN=new RewriteRuleTokenStream(adaptor,"token L_PAREN");
		RewriteRuleTokenStream stream_VALUES=new RewriteRuleTokenStream(adaptor,"token VALUES");
		RewriteRuleSubtreeStream stream_literal_list=new RewriteRuleSubtreeStream(adaptor,"rule literal_list");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:94:12: ( INSERT INTO tn= table_name VALUES L_PAREN vl= literal_list R_PAREN -> ^( INSERT $tn $vl) )
			// HSQLParser.g:95:5: INSERT INTO tn= table_name VALUES L_PAREN vl= literal_list R_PAREN
			{
			INSERT56=(Token)match(input,INSERT,FOLLOW_INSERT_in_insert_stat665);  
			stream_INSERT.add(INSERT56);

			INTO57=(Token)match(input,INTO,FOLLOW_INTO_in_insert_stat667);  
			stream_INTO.add(INTO57);

			pushFollow(FOLLOW_table_name_in_insert_stat671);
			tn=table_name();
			state._fsp--;

			stream_table_name.add(tn.getTree());
			VALUES58=(Token)match(input,VALUES,FOLLOW_VALUES_in_insert_stat673);  
			stream_VALUES.add(VALUES58);

			L_PAREN59=(Token)match(input,L_PAREN,FOLLOW_L_PAREN_in_insert_stat675);  
			stream_L_PAREN.add(L_PAREN59);

			pushFollow(FOLLOW_literal_list_in_insert_stat679);
			vl=literal_list();
			state._fsp--;

			stream_literal_list.add(vl.getTree());
			R_PAREN60=(Token)match(input,R_PAREN,FOLLOW_R_PAREN_in_insert_stat681);  
			stream_R_PAREN.add(R_PAREN60);

			// AST REWRITE
			// elements: vl, tn, INSERT
			// token labels: 
			// rule labels: vl, tn, retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_vl=new RewriteRuleSubtreeStream(adaptor,"rule vl",vl!=null?vl.getTree():null);
			RewriteRuleSubtreeStream stream_tn=new RewriteRuleSubtreeStream(adaptor,"rule tn",tn!=null?tn.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 96:5: -> ^( INSERT $tn $vl)
			{
				// HSQLParser.g:96:7: ^( INSERT $tn $vl)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_INSERT.nextNode(), root_1);
				adaptor.addChild(root_1, stream_tn.nextTree());
				adaptor.addChild(root_1, stream_vl.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insert_stat"


	public static class drop_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "drop_stat"
	// HSQLParser.g:98:1: drop_stat : DROP TABLE tn= table_name -> ^( DROP $tn) ;
	public final HSQLParser.drop_stat_return drop_stat() throws RecognitionException {
		HSQLParser.drop_stat_return retval = new HSQLParser.drop_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token DROP61=null;
		Token TABLE62=null;
		ParserRuleReturnScope tn =null;

		Object DROP61_tree=null;
		Object TABLE62_tree=null;
		RewriteRuleTokenStream stream_TABLE=new RewriteRuleTokenStream(adaptor,"token TABLE");
		RewriteRuleTokenStream stream_DROP=new RewriteRuleTokenStream(adaptor,"token DROP");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");

		try {
			// HSQLParser.g:98:10: ( DROP TABLE tn= table_name -> ^( DROP $tn) )
			// HSQLParser.g:99:5: DROP TABLE tn= table_name
			{
			DROP61=(Token)match(input,DROP,FOLLOW_DROP_in_drop_stat711);  
			stream_DROP.add(DROP61);

			TABLE62=(Token)match(input,TABLE,FOLLOW_TABLE_in_drop_stat713);  
			stream_TABLE.add(TABLE62);

			pushFollow(FOLLOW_table_name_in_drop_stat717);
			tn=table_name();
			state._fsp--;

			stream_table_name.add(tn.getTree());
			// AST REWRITE
			// elements: tn, DROP
			// token labels: 
			// rule labels: tn, retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_tn=new RewriteRuleSubtreeStream(adaptor,"rule tn",tn!=null?tn.getTree():null);
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 100:5: -> ^( DROP $tn)
			{
				// HSQLParser.g:100:8: ^( DROP $tn)
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_DROP.nextNode(), root_1);
				adaptor.addChild(root_1, stream_tn.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "drop_stat"


	public static class create_stat_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "create_stat"
	// HSQLParser.g:102:1: create_stat : CREATE TABLE table_name L_PAREN column_withtype_list R_PAREN -> ^( CREATE table_name column_withtype_list ) ;
	public final HSQLParser.create_stat_return create_stat() throws RecognitionException {
		HSQLParser.create_stat_return retval = new HSQLParser.create_stat_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token CREATE63=null;
		Token TABLE64=null;
		Token L_PAREN66=null;
		Token R_PAREN68=null;
		ParserRuleReturnScope table_name65 =null;
		ParserRuleReturnScope column_withtype_list67 =null;

		Object CREATE63_tree=null;
		Object TABLE64_tree=null;
		Object L_PAREN66_tree=null;
		Object R_PAREN68_tree=null;
		RewriteRuleTokenStream stream_TABLE=new RewriteRuleTokenStream(adaptor,"token TABLE");
		RewriteRuleTokenStream stream_CREATE=new RewriteRuleTokenStream(adaptor,"token CREATE");
		RewriteRuleTokenStream stream_R_PAREN=new RewriteRuleTokenStream(adaptor,"token R_PAREN");
		RewriteRuleTokenStream stream_L_PAREN=new RewriteRuleTokenStream(adaptor,"token L_PAREN");
		RewriteRuleSubtreeStream stream_table_name=new RewriteRuleSubtreeStream(adaptor,"rule table_name");
		RewriteRuleSubtreeStream stream_column_withtype_list=new RewriteRuleSubtreeStream(adaptor,"rule column_withtype_list");

		try {
			// HSQLParser.g:103:5: ( CREATE TABLE table_name L_PAREN column_withtype_list R_PAREN -> ^( CREATE table_name column_withtype_list ) )
			// HSQLParser.g:103:6: CREATE TABLE table_name L_PAREN column_withtype_list R_PAREN
			{
			CREATE63=(Token)match(input,CREATE,FOLLOW_CREATE_in_create_stat745);  
			stream_CREATE.add(CREATE63);

			TABLE64=(Token)match(input,TABLE,FOLLOW_TABLE_in_create_stat747);  
			stream_TABLE.add(TABLE64);

			pushFollow(FOLLOW_table_name_in_create_stat749);
			table_name65=table_name();
			state._fsp--;

			stream_table_name.add(table_name65.getTree());
			L_PAREN66=(Token)match(input,L_PAREN,FOLLOW_L_PAREN_in_create_stat751);  
			stream_L_PAREN.add(L_PAREN66);

			pushFollow(FOLLOW_column_withtype_list_in_create_stat753);
			column_withtype_list67=column_withtype_list();
			state._fsp--;

			stream_column_withtype_list.add(column_withtype_list67.getTree());
			R_PAREN68=(Token)match(input,R_PAREN,FOLLOW_R_PAREN_in_create_stat755);  
			stream_R_PAREN.add(R_PAREN68);

			// AST REWRITE
			// elements: column_withtype_list, CREATE, table_name
			// token labels: 
			// rule labels: retval
			// token list labels: 
			// rule list labels: 
			// wildcard labels: 
			retval.tree = root_0;
			RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

			root_0 = (Object)adaptor.nil();
			// 104:6: -> ^( CREATE table_name column_withtype_list )
			{
				// HSQLParser.g:104:9: ^( CREATE table_name column_withtype_list )
				{
				Object root_1 = (Object)adaptor.nil();
				root_1 = (Object)adaptor.becomeRoot(stream_CREATE.nextNode(), root_1);
				adaptor.addChild(root_1, stream_table_name.nextTree());
				adaptor.addChild(root_1, stream_column_withtype_list.nextTree());
				adaptor.addChild(root_0, root_1);
				}

			}


			retval.tree = root_0;

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "create_stat"


	public static class table_name_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "table_name"
	// HSQLParser.g:106:1: table_name : STR ;
	public final HSQLParser.table_name_return table_name() throws RecognitionException {
		HSQLParser.table_name_return retval = new HSQLParser.table_name_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STR69=null;

		Object STR69_tree=null;

		try {
			// HSQLParser.g:107:5: ( STR )
			// HSQLParser.g:107:6: STR
			{
			root_0 = (Object)adaptor.nil();


			STR69=(Token)match(input,STR,FOLLOW_STR_in_table_name785); 
			STR69_tree = (Object)adaptor.create(STR69);
			adaptor.addChild(root_0, STR69_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_name"


	public static class column_withtype_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "column_withtype_list"
	// HSQLParser.g:109:1: column_withtype_list : column_withtype ( COMMA column_withtype )* ;
	public final HSQLParser.column_withtype_list_return column_withtype_list() throws RecognitionException {
		HSQLParser.column_withtype_list_return retval = new HSQLParser.column_withtype_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA71=null;
		ParserRuleReturnScope column_withtype70 =null;
		ParserRuleReturnScope column_withtype72 =null;

		Object COMMA71_tree=null;

		try {
			// HSQLParser.g:110:5: ( column_withtype ( COMMA column_withtype )* )
			// HSQLParser.g:110:6: column_withtype ( COMMA column_withtype )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_column_withtype_in_column_withtype_list800);
			column_withtype70=column_withtype();
			state._fsp--;

			adaptor.addChild(root_0, column_withtype70.getTree());

			// HSQLParser.g:110:22: ( COMMA column_withtype )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==COMMA) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// HSQLParser.g:110:23: COMMA column_withtype
					{
					COMMA71=(Token)match(input,COMMA,FOLLOW_COMMA_in_column_withtype_list803); 
					COMMA71_tree = (Object)adaptor.create(COMMA71);
					adaptor.addChild(root_0, COMMA71_tree);

					pushFollow(FOLLOW_column_withtype_in_column_withtype_list805);
					column_withtype72=column_withtype();
					state._fsp--;

					adaptor.addChild(root_0, column_withtype72.getTree());

					}
					break;

				default :
					break loop12;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_withtype_list"


	public static class column_withtype_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "column_withtype"
	// HSQLParser.g:112:1: column_withtype : column type ;
	public final HSQLParser.column_withtype_return column_withtype() throws RecognitionException {
		HSQLParser.column_withtype_return retval = new HSQLParser.column_withtype_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope column73 =null;
		ParserRuleReturnScope type74 =null;


		try {
			// HSQLParser.g:113:5: ( column type )
			// HSQLParser.g:113:6: column type
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_column_in_column_withtype822);
			column73=column();
			state._fsp--;

			adaptor.addChild(root_0, column73.getTree());

			pushFollow(FOLLOW_type_in_column_withtype824);
			type74=type();
			state._fsp--;

			adaptor.addChild(root_0, type74.getTree());

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_withtype"


	public static class column_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "column"
	// HSQLParser.g:115:1: column : STR ;
	public final HSQLParser.column_return column() throws RecognitionException {
		HSQLParser.column_return retval = new HSQLParser.column_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token STR75=null;

		Object STR75_tree=null;

		try {
			// HSQLParser.g:116:5: ( STR )
			// HSQLParser.g:117:5: STR
			{
			root_0 = (Object)adaptor.nil();


			STR75=(Token)match(input,STR,FOLLOW_STR_in_column844); 
			STR75_tree = (Object)adaptor.create(STR75);
			adaptor.addChild(root_0, STR75_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column"


	public static class attr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "attr"
	// HSQLParser.g:119:1: attr : PRIMARYKEY ;
	public final HSQLParser.attr_return attr() throws RecognitionException {
		HSQLParser.attr_return retval = new HSQLParser.attr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token PRIMARYKEY76=null;

		Object PRIMARYKEY76_tree=null;

		try {
			// HSQLParser.g:119:5: ( PRIMARYKEY )
			// HSQLParser.g:120:5: PRIMARYKEY
			{
			root_0 = (Object)adaptor.nil();


			PRIMARYKEY76=(Token)match(input,PRIMARYKEY,FOLLOW_PRIMARYKEY_in_attr859); 
			PRIMARYKEY76_tree = (Object)adaptor.create(PRIMARYKEY76);
			adaptor.addChild(root_0, PRIMARYKEY76_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "attr"


	public static class type_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "type"
	// HSQLParser.g:122:1: type : ( INT | STRING | DOUBLE | LONG );
	public final HSQLParser.type_return type() throws RecognitionException {
		HSQLParser.type_return retval = new HSQLParser.type_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set77=null;

		Object set77_tree=null;

		try {
			// HSQLParser.g:123:5: ( INT | STRING | DOUBLE | LONG )
			// HSQLParser.g:
			{
			root_0 = (Object)adaptor.nil();


			set77=input.LT(1);
			if ( input.LA(1)==DOUBLE||input.LA(1)==INT||input.LA(1)==LONG||input.LA(1)==STRING ) {
				input.consume();
				adaptor.addChild(root_0, (Object)adaptor.create(set77));
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type"


	public static class literals_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "literals"
	// HSQLParser.g:128:1: literals : ( ( MINUS )? ( INTEGER_LITERAL | LONG_LITERAL | DOUBLE_LITERAL ) | STRING_LITERAL );
	public final HSQLParser.literals_return literals() throws RecognitionException {
		HSQLParser.literals_return retval = new HSQLParser.literals_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token MINUS78=null;
		Token set79=null;
		Token STRING_LITERAL80=null;

		Object MINUS78_tree=null;
		Object set79_tree=null;
		Object STRING_LITERAL80_tree=null;

		try {
			// HSQLParser.g:130:5: ( ( MINUS )? ( INTEGER_LITERAL | LONG_LITERAL | DOUBLE_LITERAL ) | STRING_LITERAL )
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==DOUBLE_LITERAL||LA14_0==INTEGER_LITERAL||LA14_0==LONG_LITERAL||LA14_0==MINUS) ) {
				alt14=1;
			}
			else if ( (LA14_0==STRING_LITERAL) ) {
				alt14=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}

			switch (alt14) {
				case 1 :
					// HSQLParser.g:130:6: ( MINUS )? ( INTEGER_LITERAL | LONG_LITERAL | DOUBLE_LITERAL )
					{
					root_0 = (Object)adaptor.nil();


					// HSQLParser.g:130:6: ( MINUS )?
					int alt13=2;
					int LA13_0 = input.LA(1);
					if ( (LA13_0==MINUS) ) {
						alt13=1;
					}
					switch (alt13) {
						case 1 :
							// HSQLParser.g:130:6: MINUS
							{
							MINUS78=(Token)match(input,MINUS,FOLLOW_MINUS_in_literals911); 
							MINUS78_tree = (Object)adaptor.create(MINUS78);
							adaptor.addChild(root_0, MINUS78_tree);

							}
							break;

					}

					set79=input.LT(1);
					if ( input.LA(1)==DOUBLE_LITERAL||input.LA(1)==INTEGER_LITERAL||input.LA(1)==LONG_LITERAL ) {
						input.consume();
						adaptor.addChild(root_0, (Object)adaptor.create(set79));
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 2 :
					// HSQLParser.g:134:6: STRING_LITERAL
					{
					root_0 = (Object)adaptor.nil();


					STRING_LITERAL80=(Token)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_literals941); 
					STRING_LITERAL80_tree = (Object)adaptor.create(STRING_LITERAL80);
					adaptor.addChild(root_0, STRING_LITERAL80_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literals"


	public static class literal_list_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "literal_list"
	// HSQLParser.g:136:1: literal_list : literals ( COMMA literals )* ;
	public final HSQLParser.literal_list_return literal_list() throws RecognitionException {
		HSQLParser.literal_list_return retval = new HSQLParser.literal_list_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token COMMA82=null;
		ParserRuleReturnScope literals81 =null;
		ParserRuleReturnScope literals83 =null;

		Object COMMA82_tree=null;

		try {
			// HSQLParser.g:137:5: ( literals ( COMMA literals )* )
			// HSQLParser.g:137:6: literals ( COMMA literals )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_literals_in_literal_list956);
			literals81=literals();
			state._fsp--;

			adaptor.addChild(root_0, literals81.getTree());

			// HSQLParser.g:137:15: ( COMMA literals )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==COMMA) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// HSQLParser.g:137:16: COMMA literals
					{
					COMMA82=(Token)match(input,COMMA,FOLLOW_COMMA_in_literal_list959); 
					COMMA82_tree = (Object)adaptor.create(COMMA82);
					adaptor.addChild(root_0, COMMA82_tree);

					pushFollow(FOLLOW_literals_in_literal_list961);
					literals83=literals();
					state._fsp--;

					adaptor.addChild(root_0, literals83.getTree());

					}
					break;

				default :
					break loop15;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literal_list"

	// Delegated rules



	public static final BitSet FOLLOW_create_stat_in_sql_stat59 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_drop_stat_in_sql_stat66 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_stat_in_sql_stat73 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_stat_in_sql_stat80 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_stat_in_sql_stat87 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_stat_in_sql_stat94 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_create_index_in_sql_stat101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_create_index121 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_INDEX_in_create_index123 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_index_name_in_create_index125 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_ON_in_create_index127 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_create_index129 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STR_in_index_name157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_UPDATE_in_update_stat177 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_update_stat179 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_update_list_in_update_stat185 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_where_clause_in_update_stat191 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SET_in_update_list224 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_update_element_in_update_list227 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_update_list230 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_update_element_in_update_list233 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_column_in_update_element250 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_EQ_in_update_element252 = new BitSet(new long[]{0x0000100222008000L});
	public static final BitSet FOLLOW_expression_in_update_element254 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literals_in_expression283 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_delete_stat303 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_FROM_in_delete_stat306 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_delete_stat309 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_where_clause_in_delete_stat311 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHERE_in_where_clause347 = new BitSet(new long[]{0x0000040100000000L});
	public static final BitSet FOLLOW_andCondition_in_where_clause349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_orCondition_in_andCondition382 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_AND_in_andCondition385 = new BitSet(new long[]{0x0000040100000000L});
	public static final BitSet FOLLOW_orCondition_in_andCondition388 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_atomCondition_in_orCondition410 = new BitSet(new long[]{0x0000001000000002L});
	public static final BitSet FOLLOW_OR_in_orCondition413 = new BitSet(new long[]{0x0000040100000000L});
	public static final BitSet FOLLOW_atomCondition_in_orCondition416 = new BitSet(new long[]{0x0000001000000002L});
	public static final BitSet FOLLOW_column_in_atomCondition439 = new BitSet(new long[]{0x00000000C0340000L});
	public static final BitSet FOLLOW_comp_op_in_atomCondition441 = new BitSet(new long[]{0x0000100222008000L});
	public static final BitSet FOLLOW_expression_in_atomCondition443 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_L_PAREN_in_atomCondition461 = new BitSet(new long[]{0x0000040100000000L});
	public static final BitSet FOLLOW_andCondition_in_atomCondition463 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_R_PAREN_in_atomCondition465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_select_stat533 = new BitSet(new long[]{0x0000440100000000L});
	public static final BitSet FOLLOW_select_list_in_select_stat537 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_FROM_in_select_stat539 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_select_stat543 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_where_clause_in_select_stat547 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_projector_in_select_list579 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_select_list582 = new BitSet(new long[]{0x0000440100000000L});
	public static final BitSet FOLLOW_projector_in_select_list584 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_column_in_projector614 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fun_in_projector621 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_L_PAREN_in_projector623 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_column_in_projector625 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_R_PAREN_in_projector627 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SUM_in_fun650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INSERT_in_insert_stat665 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_INTO_in_insert_stat667 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_insert_stat671 = new BitSet(new long[]{0x0002000000000000L});
	public static final BitSet FOLLOW_VALUES_in_insert_stat673 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_L_PAREN_in_insert_stat675 = new BitSet(new long[]{0x0000100222008000L});
	public static final BitSet FOLLOW_literal_list_in_insert_stat679 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_R_PAREN_in_insert_stat681 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DROP_in_drop_stat711 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_TABLE_in_drop_stat713 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_drop_stat717 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_create_stat745 = new BitSet(new long[]{0x0000800000000000L});
	public static final BitSet FOLLOW_TABLE_in_create_stat747 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_create_stat749 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_L_PAREN_in_create_stat751 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_column_withtype_list_in_create_stat753 = new BitSet(new long[]{0x0000008000000000L});
	public static final BitSet FOLLOW_R_PAREN_in_create_stat755 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STR_in_table_name785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_withtype_in_column_withtype_list800 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_column_withtype_list803 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_column_withtype_in_column_withtype_list805 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_column_in_column_withtype822 = new BitSet(new long[]{0x0000080011004000L});
	public static final BitSet FOLLOW_type_in_column_withtype824 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STR_in_column844 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRIMARYKEY_in_attr859 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_literals911 = new BitSet(new long[]{0x0000000022008000L});
	public static final BitSet FOLLOW_set_in_literals913 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_literals941 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literals_in_literal_list956 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_literal_list959 = new BitSet(new long[]{0x0000100222008000L});
	public static final BitSet FOLLOW_literals_in_literal_list961 = new BitSet(new long[]{0x0000000000000082L});
}
