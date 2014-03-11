// $ANTLR 3.5 HSQLTreeParser.g 2014-02-24 14:43:15

package org.ict.hsql.parse;
import org.ict.hsql.plan.*;
import org.ict.hsql.schema.*;
import org.ict.hsql.query.*;
import org.ict.hsql.utils.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.ict.hsql.Algorithm.*;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


@SuppressWarnings("all")
public class HSQLTreeParser extends TreeParser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "AND", "CHAR", "CHAR_ESC", "COMMA", 
		"CREATE", "DBL_QUOTE_CHAR", "DECIMAL", "DELETE", "DIGIT", "DOT", "DOUBLE", 
		"DOUBLE_LITERAL", "DROP", "EOL", "EQ", "FROM", "GT", "GTE", "INDEX", "INSERT", 
		"INT", "INTEGER_LITERAL", "INTO", "LETTER", "LONG", "LONG_LITERAL", "LT", 
		"LTE", "L_PAREN", "MINUS", "NEQ", "ON", "OR", "POSINTEGER", "PRIMARYKEY", 
		"R_PAREN", "SELECT", "SET", "STR", "STRING", "STRING_LITERAL", "STR_LITERALS", 
		"SUM", "TABLE", "UPDATE", "VALUES", "WHERE", "WS", "LIST", "SELECT_LIST", 
		"R_AREN"
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
	public static final int R_AREN=54;

	// delegates
	public TreeParser[] getDelegates() {
		return new TreeParser[] {};
	}

	// delegators


	public HSQLTreeParser(TreeNodeStream input) {
		this(input, new RecognizerSharedState());
	}
	public HSQLTreeParser(TreeNodeStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return HSQLTreeParser.tokenNames; }
	@Override public String getGrammarFileName() { return "HSQLTreeParser.g"; }


	public static class sql_stat_return extends TreeRuleReturnScope {
		public QueryPlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "sql_stat"
	// HSQLTreeParser.g:19:1: sql_stat returns [QueryPlan plan] : (c= create_stat |d= drop_stat |i= insert_stat |s= select_stat |de= delete_stat |u= update_stat |ci= create_index );
	public final HSQLTreeParser.sql_stat_return sql_stat() throws RecognitionException {
		HSQLTreeParser.sql_stat_return retval = new HSQLTreeParser.sql_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope c =null;
		TreeRuleReturnScope d =null;
		TreeRuleReturnScope i =null;
		TreeRuleReturnScope s =null;
		TreeRuleReturnScope de =null;
		TreeRuleReturnScope u =null;
		TreeRuleReturnScope ci =null;


		try {
			// HSQLTreeParser.g:21:5: (c= create_stat |d= drop_stat |i= insert_stat |s= select_stat |de= delete_stat |u= update_stat |ci= create_index )
			int alt1=7;
			switch ( input.LA(1) ) {
			case CREATE:
				{
				alt1=1;
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
			case INDEX:
				{
				alt1=7;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// HSQLTreeParser.g:21:6: c= create_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_create_stat_in_sql_stat53);
					c=create_stat();
					state._fsp--;

					adaptor.addChild(root_0, c.getTree());

					retval.plan =(c!=null?((HSQLTreeParser.create_stat_return)c).plan:null);
					}
					break;
				case 2 :
					// HSQLTreeParser.g:22:6: d= drop_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_drop_stat_in_sql_stat64);
					d=drop_stat();
					state._fsp--;

					adaptor.addChild(root_0, d.getTree());

					retval.plan =(d!=null?((HSQLTreeParser.drop_stat_return)d).plan:null);
					}
					break;
				case 3 :
					// HSQLTreeParser.g:23:6: i= insert_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_insert_stat_in_sql_stat75);
					i=insert_stat();
					state._fsp--;

					adaptor.addChild(root_0, i.getTree());

					retval.plan =(i!=null?((HSQLTreeParser.insert_stat_return)i).plan:null);
					}
					break;
				case 4 :
					// HSQLTreeParser.g:24:6: s= select_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_select_stat_in_sql_stat86);
					s=select_stat();
					state._fsp--;

					adaptor.addChild(root_0, s.getTree());

					retval.plan =(s!=null?((HSQLTreeParser.select_stat_return)s).plan:null);
					}
					break;
				case 5 :
					// HSQLTreeParser.g:25:6: de= delete_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_delete_stat_in_sql_stat97);
					de=delete_stat();
					state._fsp--;

					adaptor.addChild(root_0, de.getTree());

					retval.plan =(de!=null?((HSQLTreeParser.delete_stat_return)de).plan:null);
					}
					break;
				case 6 :
					// HSQLTreeParser.g:26:6: u= update_stat
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_update_stat_in_sql_stat108);
					u=update_stat();
					state._fsp--;

					adaptor.addChild(root_0, u.getTree());

					retval.plan =(u!=null?((HSQLTreeParser.update_stat_return)u).plan:null);
					}
					break;
				case 7 :
					// HSQLTreeParser.g:27:6: ci= create_index
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_create_index_in_sql_stat119);
					ci=create_index();
					state._fsp--;

					adaptor.addChild(root_0, ci.getTree());

					retval.plan =(ci!=null?((HSQLTreeParser.create_index_return)ci).plan:null);
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sql_stat"


	public static class create_index_return extends TreeRuleReturnScope {
		public CreateIndexPlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "create_index"
	// HSQLTreeParser.g:29:1: create_index returns [CreateIndexPlan plan] : ^( INDEX in= index_name tn= table_name ) ;
	public final HSQLTreeParser.create_index_return create_index() throws RecognitionException {
		HSQLTreeParser.create_index_return retval = new HSQLTreeParser.create_index_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INDEX1=null;
		TreeRuleReturnScope in =null;
		TreeRuleReturnScope tn =null;

		CommonTree INDEX1_tree=null;


		        retval.plan =new CreateIndexPlan();
		    
		try {
			// HSQLTreeParser.g:34:5: ( ^( INDEX in= index_name tn= table_name ) )
			// HSQLTreeParser.g:34:6: ^( INDEX in= index_name tn= table_name )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			INDEX1=(CommonTree)match(input,INDEX,FOLLOW_INDEX_in_create_index152); 
			INDEX1_tree = (CommonTree)adaptor.dupNode(INDEX1);


			root_1 = (CommonTree)adaptor.becomeRoot(INDEX1_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_index_name_in_create_index156);
			in=index_name();
			state._fsp--;

			adaptor.addChild(root_1, in.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_create_index160);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty(HSQLPropertyConstants.TABLENAME,(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        retval.plan.setProperty(HSQLPropertyConstants.INDEXNAME,(in!=null?((HSQLTreeParser.index_name_return)in).indexName:null));
			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "create_index"


	public static class update_stat_return extends TreeRuleReturnScope {
		public UpdatePlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "update_stat"
	// HSQLTreeParser.g:40:1: update_stat returns [UpdatePlan plan] : ^( UPDATE tn= table_name ul= update_list (w= where_clause )? ) ;
	public final HSQLTreeParser.update_stat_return update_stat() throws RecognitionException {
		HSQLTreeParser.update_stat_return retval = new HSQLTreeParser.update_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree UPDATE2=null;
		TreeRuleReturnScope tn =null;
		TreeRuleReturnScope ul =null;
		TreeRuleReturnScope w =null;

		CommonTree UPDATE2_tree=null;


		        retval.plan =new UpdatePlan();
		    
		try {
			// HSQLTreeParser.g:45:5: ( ^( UPDATE tn= table_name ul= update_list (w= where_clause )? ) )
			// HSQLTreeParser.g:46:5: ^( UPDATE tn= table_name ul= update_list (w= where_clause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			UPDATE2=(CommonTree)match(input,UPDATE,FOLLOW_UPDATE_in_update_stat203); 
			UPDATE2_tree = (CommonTree)adaptor.dupNode(UPDATE2);


			root_1 = (CommonTree)adaptor.becomeRoot(UPDATE2_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_update_stat207);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_update_list_in_update_stat211);
			ul=update_list();
			state._fsp--;

			adaptor.addChild(root_1, ul.getTree());

			// HSQLTreeParser.g:46:44: (w= where_clause )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( (LA2_0==WHERE) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// HSQLTreeParser.g:46:44: w= where_clause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_where_clause_in_update_stat215);
					w=where_clause();
					state._fsp--;

					adaptor.addChild(root_1, w.getTree());

					}
					break;

			}

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty(HSQLPropertyConstants.TABLENAME,(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        retval.plan.setProperty(HSQLPropertyConstants.UPDATE,(ul!=null?((HSQLTreeParser.update_list_return)ul).cv:null));
			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_stat"


	public static class update_list_return extends TreeRuleReturnScope {
		public List<ColumnValueRef> cv;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "update_list"
	// HSQLTreeParser.g:52:1: update_list returns [List<ColumnValueRef> cv] : ^( SET a= update_element (b= update_element )* ) ;
	public final HSQLTreeParser.update_list_return update_list() throws RecognitionException {
		HSQLTreeParser.update_list_return retval = new HSQLTreeParser.update_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SET3=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree SET3_tree=null;


		        retval.cv =new ArrayList<ColumnValueRef>();

		    
		try {
			// HSQLTreeParser.g:58:5: ( ^( SET a= update_element (b= update_element )* ) )
			// HSQLTreeParser.g:59:5: ^( SET a= update_element (b= update_element )* )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SET3=(CommonTree)match(input,SET,FOLLOW_SET_in_update_list259); 
			SET3_tree = (CommonTree)adaptor.dupNode(SET3);


			root_1 = (CommonTree)adaptor.becomeRoot(SET3_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_update_element_in_update_list263);
			a=update_element();
			state._fsp--;

			adaptor.addChild(root_1, a.getTree());

			retval.cv.add((a!=null?((HSQLTreeParser.update_element_return)a).c:null));
			// HSQLTreeParser.g:59:44: (b= update_element )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==EQ) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// HSQLTreeParser.g:59:45: b= update_element
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_update_element_in_update_list269);
					b=update_element();
					state._fsp--;

					adaptor.addChild(root_1, b.getTree());

					retval.cv.add((b!=null?((HSQLTreeParser.update_element_return)b).c:null));
					}
					break;

				default :
					break loop3;
				}
			}

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_list"


	public static class update_element_return extends TreeRuleReturnScope {
		public ColumnValueRef c;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "update_element"
	// HSQLTreeParser.g:62:1: update_element returns [ColumnValueRef c] : ^( EQ cn= column v= expression ) ;
	public final HSQLTreeParser.update_element_return update_element() throws RecognitionException {
		HSQLTreeParser.update_element_return retval = new HSQLTreeParser.update_element_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EQ4=null;
		TreeRuleReturnScope cn =null;
		TreeRuleReturnScope v =null;

		CommonTree EQ4_tree=null;

		try {
			// HSQLTreeParser.g:64:5: ( ^( EQ cn= column v= expression ) )
			// HSQLTreeParser.g:65:5: ^( EQ cn= column v= expression )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			EQ4=(CommonTree)match(input,EQ,FOLLOW_EQ_in_update_element303); 
			EQ4_tree = (CommonTree)adaptor.dupNode(EQ4);


			root_1 = (CommonTree)adaptor.becomeRoot(EQ4_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_column_in_update_element307);
			cn=column();
			state._fsp--;

			adaptor.addChild(root_1, cn.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_expression_in_update_element312);
			v=expression();
			state._fsp--;

			adaptor.addChild(root_1, v.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}




			        ColumnRef cr=new ColumnRef((cn!=null?((HSQLTreeParser.column_return)cn).name:null));
			        retval.c =new ColumnValueRef(cr,(v!=null?((HSQLTreeParser.expression_return)v).v:null));

			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "update_element"


	public static class expression_return extends TreeRuleReturnScope {
		public ValueRef v;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// HSQLTreeParser.g:73:1: expression returns [ValueRef v] : l= literals ;
	public final HSQLTreeParser.expression_return expression() throws RecognitionException {
		HSQLTreeParser.expression_return retval = new HSQLTreeParser.expression_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope l =null;


		try {
			// HSQLTreeParser.g:75:5: (l= literals )
			// HSQLTreeParser.g:75:6: l= literals
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_literals_in_expression343);
			l=literals();
			state._fsp--;

			adaptor.addChild(root_0, l.getTree());

			retval.v =(l!=null?((HSQLTreeParser.literals_return)l).v:null);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class where_clause_return extends TreeRuleReturnScope {
		public HSQLCondition cond;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "where_clause"
	// HSQLTreeParser.g:77:1: where_clause returns [HSQLCondition cond] : ^( WHERE c= andCondition ) ;
	public final HSQLTreeParser.where_clause_return where_clause() throws RecognitionException {
		HSQLTreeParser.where_clause_return retval = new HSQLTreeParser.where_clause_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree WHERE5=null;
		TreeRuleReturnScope c =null;

		CommonTree WHERE5_tree=null;

		try {
			// HSQLTreeParser.g:79:5: ( ^( WHERE c= andCondition ) )
			// HSQLTreeParser.g:79:6: ^( WHERE c= andCondition )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			WHERE5=(CommonTree)match(input,WHERE,FOLLOW_WHERE_in_where_clause368); 
			WHERE5_tree = (CommonTree)adaptor.dupNode(WHERE5);


			root_1 = (CommonTree)adaptor.becomeRoot(WHERE5_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_andCondition_in_where_clause372);
			c=andCondition();
			state._fsp--;

			adaptor.addChild(root_1, c.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			retval.cond =(c!=null?((HSQLTreeParser.andCondition_return)c).cond:null);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "where_clause"


	public static class andCondition_return extends TreeRuleReturnScope {
		public HSQLCondition cond;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "andCondition"
	// HSQLTreeParser.g:81:1: andCondition returns [HSQLCondition cond] : a= orCondition ( AND ^b= orCondition )* ;
	public final HSQLTreeParser.andCondition_return andCondition() throws RecognitionException {
		HSQLTreeParser.andCondition_return retval = new HSQLTreeParser.andCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree AND6=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree AND6_tree=null;


		        retval.cond =new HSQLAndCondition();
		    
		try {
			// HSQLTreeParser.g:86:5: (a= orCondition ( AND ^b= orCondition )* )
			// HSQLTreeParser.g:86:6: a= orCondition ( AND ^b= orCondition )*
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_orCondition_in_andCondition406);
			a=orCondition();
			state._fsp--;

			adaptor.addChild(root_0, a.getTree());

			retval.cond.add((a!=null?((HSQLTreeParser.orCondition_return)a).cond:null));
			// HSQLTreeParser.g:86:41: ( AND ^b= orCondition )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==AND) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// HSQLTreeParser.g:86:42: AND ^b= orCondition
					{
					_last = (CommonTree)input.LT(1);
					AND6=(CommonTree)match(input,AND,FOLLOW_AND_in_andCondition410); 
					AND6_tree = (CommonTree)adaptor.dupNode(AND6);


					root_0 = (CommonTree)adaptor.becomeRoot(AND6_tree, root_0);

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_orCondition_in_andCondition415);
					b=orCondition();
					state._fsp--;

					adaptor.addChild(root_0, b.getTree());

					retval.cond.add((b!=null?((HSQLTreeParser.orCondition_return)b).cond:null));
					}
					break;

				default :
					break loop4;
				}
			}

			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "andCondition"


	public static class orCondition_return extends TreeRuleReturnScope {
		public HSQLCondition cond;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "orCondition"
	// HSQLTreeParser.g:88:1: orCondition returns [HSQLCondition cond] : a= atomCondition ( OR ^b= atomCondition )* ;
	public final HSQLTreeParser.orCondition_return orCondition() throws RecognitionException {
		HSQLTreeParser.orCondition_return retval = new HSQLTreeParser.orCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree OR7=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree OR7_tree=null;


		        retval.cond =new HSQLOrCondition();
		    
		try {
			// HSQLTreeParser.g:93:5: (a= atomCondition ( OR ^b= atomCondition )* )
			// HSQLTreeParser.g:93:6: a= atomCondition ( OR ^b= atomCondition )*
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_atomCondition_in_orCondition451);
			a=atomCondition();
			state._fsp--;

			adaptor.addChild(root_0, a.getTree());

			retval.cond.add((a!=null?((HSQLTreeParser.atomCondition_return)a).cond:null));
			// HSQLTreeParser.g:93:44: ( OR ^b= atomCondition )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==OR) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// HSQLTreeParser.g:93:45: OR ^b= atomCondition
					{
					_last = (CommonTree)input.LT(1);
					OR7=(CommonTree)match(input,OR,FOLLOW_OR_in_orCondition456); 
					OR7_tree = (CommonTree)adaptor.dupNode(OR7);


					root_0 = (CommonTree)adaptor.becomeRoot(OR7_tree, root_0);

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_atomCondition_in_orCondition461);
					b=atomCondition();
					state._fsp--;

					adaptor.addChild(root_0, b.getTree());

					retval.cond.add((b!=null?((HSQLTreeParser.atomCondition_return)b).cond:null));
					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "orCondition"


	public static class atomCondition_return extends TreeRuleReturnScope {
		public HSQLCondition cond;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "atomCondition"
	// HSQLTreeParser.g:95:1: atomCondition returns [HSQLCondition cond] : ( ^(op= comp_op c= column v= expression ) | L_PAREN a= andCondition R_AREN );
	public final HSQLTreeParser.atomCondition_return atomCondition() throws RecognitionException {
		HSQLTreeParser.atomCondition_return retval = new HSQLTreeParser.atomCondition_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree L_PAREN8=null;
		CommonTree R_AREN9=null;
		TreeRuleReturnScope op =null;
		TreeRuleReturnScope c =null;
		TreeRuleReturnScope v =null;
		TreeRuleReturnScope a =null;

		CommonTree L_PAREN8_tree=null;
		CommonTree R_AREN9_tree=null;

		try {
			// HSQLTreeParser.g:97:5: ( ^(op= comp_op c= column v= expression ) | L_PAREN a= andCondition R_AREN )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==EQ||(LA6_0 >= GT && LA6_0 <= GTE)||(LA6_0 >= LT && LA6_0 <= LTE)||LA6_0==NEQ) ) {
				alt6=1;
			}
			else if ( (LA6_0==L_PAREN) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// HSQLTreeParser.g:97:7: ^(op= comp_op c= column v= expression )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_comp_op_in_atomCondition491);
					op=comp_op();
					state._fsp--;

					root_1 = (CommonTree)adaptor.becomeRoot(op.getTree(), root_1);

					match(input, Token.DOWN, null); 
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_column_in_atomCondition495);
					c=column();
					state._fsp--;

					adaptor.addChild(root_1, c.getTree());

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_expression_in_atomCondition499);
					v=expression();
					state._fsp--;

					adaptor.addChild(root_1, v.getTree());

					match(input, Token.UP, null); 
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					retval.cond =new HSQLAtomCondition(new ColumnRef((c!=null?((HSQLTreeParser.column_return)c).name:null)),(v!=null?((HSQLTreeParser.expression_return)v).v:null),(op!=null?((HSQLTreeParser.comp_op_return)op).op:null));
					}
					break;
				case 2 :
					// HSQLTreeParser.g:100:6: L_PAREN a= andCondition R_AREN
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					L_PAREN8=(CommonTree)match(input,L_PAREN,FOLLOW_L_PAREN_in_atomCondition519); 
					L_PAREN8_tree = (CommonTree)adaptor.dupNode(L_PAREN8);


					adaptor.addChild(root_0, L_PAREN8_tree);

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_andCondition_in_atomCondition523);
					a=andCondition();
					state._fsp--;

					adaptor.addChild(root_0, a.getTree());

					_last = (CommonTree)input.LT(1);
					R_AREN9=(CommonTree)match(input,R_AREN,FOLLOW_R_AREN_in_atomCondition525); 
					R_AREN9_tree = (CommonTree)adaptor.dupNode(R_AREN9);


					adaptor.addChild(root_0, R_AREN9_tree);

					retval.cond =(a!=null?((HSQLTreeParser.andCondition_return)a).cond:null);
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atomCondition"


	public static class delete_stat_return extends TreeRuleReturnScope {
		public DeletePlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "delete_stat"
	// HSQLTreeParser.g:102:1: delete_stat returns [DeletePlan plan] : ^( DELETE tn= table_name (w= where_clause )? ) ;
	public final HSQLTreeParser.delete_stat_return delete_stat() throws RecognitionException {
		HSQLTreeParser.delete_stat_return retval = new HSQLTreeParser.delete_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DELETE10=null;
		TreeRuleReturnScope tn =null;
		TreeRuleReturnScope w =null;

		CommonTree DELETE10_tree=null;


		        retval.plan =new DeletePlan();
		    
		try {
			// HSQLTreeParser.g:107:5: ( ^( DELETE tn= table_name (w= where_clause )? ) )
			// HSQLTreeParser.g:107:6: ^( DELETE tn= table_name (w= where_clause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DELETE10=(CommonTree)match(input,DELETE,FOLLOW_DELETE_in_delete_stat558); 
			DELETE10_tree = (CommonTree)adaptor.dupNode(DELETE10);


			root_1 = (CommonTree)adaptor.becomeRoot(DELETE10_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_delete_stat562);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			// HSQLTreeParser.g:107:30: (w= where_clause )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==WHERE) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// HSQLTreeParser.g:107:30: w= where_clause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_where_clause_in_delete_stat566);
					w=where_clause();
					state._fsp--;

					adaptor.addChild(root_1, w.getTree());

					}
					break;

			}

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty(HSQLPropertyConstants.TABLENAME,(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        if(w!=null){
			            retval.plan.setProperty(HSQLPropertyConstants.WHERE,(w!=null?((HSQLTreeParser.where_clause_return)w).cond:null));
			        }
			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "delete_stat"


	public static class comp_op_return extends TreeRuleReturnScope {
		public CompareFilter.CompareOp op;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "comp_op"
	// HSQLTreeParser.g:117:1: comp_op returns [CompareFilter.CompareOp op] : ( EQ | NEQ | LT | LTE | GT | GTE );
	public final HSQLTreeParser.comp_op_return comp_op() throws RecognitionException {
		HSQLTreeParser.comp_op_return retval = new HSQLTreeParser.comp_op_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree EQ11=null;
		CommonTree NEQ12=null;
		CommonTree LT13=null;
		CommonTree LTE14=null;
		CommonTree GT15=null;
		CommonTree GTE16=null;

		CommonTree EQ11_tree=null;
		CommonTree NEQ12_tree=null;
		CommonTree LT13_tree=null;
		CommonTree LTE14_tree=null;
		CommonTree GT15_tree=null;
		CommonTree GTE16_tree=null;

		try {
			// HSQLTreeParser.g:119:5: ( EQ | NEQ | LT | LTE | GT | GTE )
			int alt8=6;
			switch ( input.LA(1) ) {
			case EQ:
				{
				alt8=1;
				}
				break;
			case NEQ:
				{
				alt8=2;
				}
				break;
			case LT:
				{
				alt8=3;
				}
				break;
			case LTE:
				{
				alt8=4;
				}
				break;
			case GT:
				{
				alt8=5;
				}
				break;
			case GTE:
				{
				alt8=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}
			switch (alt8) {
				case 1 :
					// HSQLTreeParser.g:120:5: EQ
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					EQ11=(CommonTree)match(input,EQ,FOLLOW_EQ_in_comp_op603); 
					EQ11_tree = (CommonTree)adaptor.dupNode(EQ11);


					adaptor.addChild(root_0, EQ11_tree);

					retval.op =CompareFilter.CompareOp.EQUAL;
					}
					break;
				case 2 :
					// HSQLTreeParser.g:121:6: NEQ
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					NEQ12=(CommonTree)match(input,NEQ,FOLLOW_NEQ_in_comp_op612); 
					NEQ12_tree = (CommonTree)adaptor.dupNode(NEQ12);


					adaptor.addChild(root_0, NEQ12_tree);

					retval.op = CompareFilter.CompareOp.NOT_EQUAL;
					}
					break;
				case 3 :
					// HSQLTreeParser.g:122:6: LT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LT13=(CommonTree)match(input,LT,FOLLOW_LT_in_comp_op621); 
					LT13_tree = (CommonTree)adaptor.dupNode(LT13);


					adaptor.addChild(root_0, LT13_tree);

					retval.op =   CompareFilter.CompareOp.LESS;
					}
					break;
				case 4 :
					// HSQLTreeParser.g:123:6: LTE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LTE14=(CommonTree)match(input,LTE,FOLLOW_LTE_in_comp_op631); 
					LTE14_tree = (CommonTree)adaptor.dupNode(LTE14);


					adaptor.addChild(root_0, LTE14_tree);

					retval.op =  CompareFilter.CompareOp.LESS_OR_EQUAL;
					}
					break;
				case 5 :
					// HSQLTreeParser.g:124:6: GT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					GT15=(CommonTree)match(input,GT,FOLLOW_GT_in_comp_op640); 
					GT15_tree = (CommonTree)adaptor.dupNode(GT15);


					adaptor.addChild(root_0, GT15_tree);

					retval.op =  CompareFilter.CompareOp.GREATER; 
					}
					break;
				case 6 :
					// HSQLTreeParser.g:125:6: GTE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					GTE16=(CommonTree)match(input,GTE,FOLLOW_GTE_in_comp_op650); 
					GTE16_tree = (CommonTree)adaptor.dupNode(GTE16);


					adaptor.addChild(root_0, GTE16_tree);

					retval.op =  CompareFilter.CompareOp.GREATER_OR_EQUAL;
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_op"


	public static class select_stat_return extends TreeRuleReturnScope {
		public SelectPlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "select_stat"
	// HSQLTreeParser.g:128:1: select_stat returns [SelectPlan plan] : ^( SELECT s= select_list tn= table_name (w= where_clause )? ) ;
	public final HSQLTreeParser.select_stat_return select_stat() throws RecognitionException {
		HSQLTreeParser.select_stat_return retval = new HSQLTreeParser.select_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SELECT17=null;
		TreeRuleReturnScope s =null;
		TreeRuleReturnScope tn =null;
		TreeRuleReturnScope w =null;

		CommonTree SELECT17_tree=null;


		        retval.plan =new SelectPlan();
		        HSQLCondition cond=null;
		    
		try {
			// HSQLTreeParser.g:134:5: ( ^( SELECT s= select_list tn= table_name (w= where_clause )? ) )
			// HSQLTreeParser.g:134:6: ^( SELECT s= select_list tn= table_name (w= where_clause )? )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SELECT17=(CommonTree)match(input,SELECT,FOLLOW_SELECT_in_select_stat684); 
			SELECT17_tree = (CommonTree)adaptor.dupNode(SELECT17);


			root_1 = (CommonTree)adaptor.becomeRoot(SELECT17_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_select_list_in_select_stat688);
			s=select_list();
			state._fsp--;

			adaptor.addChild(root_1, s.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_select_stat692);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			// HSQLTreeParser.g:134:43: (w= where_clause )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0==WHERE) ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// HSQLTreeParser.g:134:44: w= where_clause
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_where_clause_in_select_stat697);
					w=where_clause();
					state._fsp--;

					adaptor.addChild(root_1, w.getTree());

					cond=(w!=null?((HSQLTreeParser.where_clause_return)w).cond:null);
					}
					break;

			}

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty("TABLENAME",(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        retval.plan.setProperty(HSQLPropertyConstants.PROJECTORS,(s!=null?((HSQLTreeParser.select_list_return)s).projectors:null));

			        if(cond!=null){
			            retval.plan.setProperty(HSQLPropertyConstants.WHERE,cond);
			        }

			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_stat"


	public static class select_list_return extends TreeRuleReturnScope {
		public List<Projector> projectors;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "select_list"
	// HSQLTreeParser.g:146:1: select_list returns [List<Projector> projectors] : ^( SELECT_LIST (p= projector )+ ) ;
	public final HSQLTreeParser.select_list_return select_list() throws RecognitionException {
		HSQLTreeParser.select_list_return retval = new HSQLTreeParser.select_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SELECT_LIST18=null;
		TreeRuleReturnScope p =null;

		CommonTree SELECT_LIST18_tree=null;


		        retval.projectors =new ArrayList<Projector>();
		    
		try {
			// HSQLTreeParser.g:151:5: ( ^( SELECT_LIST (p= projector )+ ) )
			// HSQLTreeParser.g:152:5: ^( SELECT_LIST (p= projector )+ )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			SELECT_LIST18=(CommonTree)match(input,SELECT_LIST,FOLLOW_SELECT_LIST_in_select_list745); 
			SELECT_LIST18_tree = (CommonTree)adaptor.dupNode(SELECT_LIST18);


			root_1 = (CommonTree)adaptor.becomeRoot(SELECT_LIST18_tree, root_1);

			match(input, Token.DOWN, null); 
			// HSQLTreeParser.g:152:19: (p= projector )+
			int cnt10=0;
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==STR||LA10_0==SUM) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// HSQLTreeParser.g:152:20: p= projector
					{
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_projector_in_select_list750);
					p=projector();
					state._fsp--;

					adaptor.addChild(root_1, p.getTree());

					retval.projectors.add((p!=null?((HSQLTreeParser.projector_return)p).p:null));
					}
					break;

				default :
					if ( cnt10 >= 1 ) break loop10;
					EarlyExitException eee = new EarlyExitException(10, input);
					throw eee;
				}
				cnt10++;
			}

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}


			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "select_list"


	public static class projector_return extends TreeRuleReturnScope {
		public Projector p;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "projector"
	// HSQLTreeParser.g:156:1: projector returns [Projector p] : (c= column | ^(f= fun c= column ) );
	public final HSQLTreeParser.projector_return projector() throws RecognitionException {
		HSQLTreeParser.projector_return retval = new HSQLTreeParser.projector_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope c =null;
		TreeRuleReturnScope f =null;


		try {
			// HSQLTreeParser.g:158:5: (c= column | ^(f= fun c= column ) )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==STR) ) {
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
					// HSQLTreeParser.g:159:5: c= column
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_column_in_projector785);
					c=column();
					state._fsp--;

					adaptor.addChild(root_0, c.getTree());

					retval.p =new Projector((c!=null?((HSQLTreeParser.column_return)c).name:null));
					}
					break;
				case 2 :
					// HSQLTreeParser.g:160:6: ^(f= fun c= column )
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					{
					CommonTree _save_last_1 = _last;
					CommonTree _first_1 = null;
					CommonTree root_1 = (CommonTree)adaptor.nil();
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_fun_in_projector796);
					f=fun();
					state._fsp--;

					root_1 = (CommonTree)adaptor.becomeRoot(f.getTree(), root_1);

					match(input, Token.DOWN, null); 
					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_column_in_projector800);
					c=column();
					state._fsp--;

					adaptor.addChild(root_1, c.getTree());

					match(input, Token.UP, null); 
					adaptor.addChild(root_0, root_1);
					_last = _save_last_1;
					}


					retval.p =new Projector((c!=null?((HSQLTreeParser.column_return)c).name:null),(f!=null?((HSQLTreeParser.fun_return)f).fun:null));
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "projector"


	public static class fun_return extends TreeRuleReturnScope {
		public Func fun;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "fun"
	// HSQLTreeParser.g:162:1: fun returns [Func fun] : SUM ;
	public final HSQLTreeParser.fun_return fun() throws RecognitionException {
		HSQLTreeParser.fun_return retval = new HSQLTreeParser.fun_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree SUM19=null;

		CommonTree SUM19_tree=null;

		try {
			// HSQLTreeParser.g:164:5: ( SUM )
			// HSQLTreeParser.g:164:6: SUM
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			SUM19=(CommonTree)match(input,SUM,FOLLOW_SUM_in_fun825); 
			SUM19_tree = (CommonTree)adaptor.dupNode(SUM19);


			adaptor.addChild(root_0, SUM19_tree);

			retval.fun =new Func(Func.TYPE.SUM);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "fun"


	public static class create_stat_return extends TreeRuleReturnScope {
		public CreateTablePlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "create_stat"
	// HSQLTreeParser.g:167:1: create_stat returns [CreateTablePlan plan] : ^( CREATE tn= table_name cl= column_withtype_list ) ;
	public final HSQLTreeParser.create_stat_return create_stat() throws RecognitionException {
		HSQLTreeParser.create_stat_return retval = new HSQLTreeParser.create_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree CREATE20=null;
		TreeRuleReturnScope tn =null;
		TreeRuleReturnScope cl =null;

		CommonTree CREATE20_tree=null;


		        retval.plan =new CreateTablePlan();
		    
		try {
			// HSQLTreeParser.g:172:5: ( ^( CREATE tn= table_name cl= column_withtype_list ) )
			// HSQLTreeParser.g:172:6: ^( CREATE tn= table_name cl= column_withtype_list )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			CREATE20=(CommonTree)match(input,CREATE,FOLLOW_CREATE_in_create_stat859); 
			CREATE20_tree = (CommonTree)adaptor.dupNode(CREATE20);


			root_1 = (CommonTree)adaptor.becomeRoot(CREATE20_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_create_stat863);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_column_withtype_list_in_create_stat867);
			cl=column_withtype_list();
			state._fsp--;

			adaptor.addChild(root_1, cl.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty("TABLENAME",(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        retval.plan.setProperty("TABLESCHEMA",new TableSchema((tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null),(cl!=null?((HSQLTreeParser.column_withtype_list_return)cl).columns:null)));
			     
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "create_stat"


	public static class insert_stat_return extends TreeRuleReturnScope {
		public InsertRecordsPlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "insert_stat"
	// HSQLTreeParser.g:178:1: insert_stat returns [InsertRecordsPlan plan] : ^( INSERT tn= table_name vl= literal_list ) ;
	public final HSQLTreeParser.insert_stat_return insert_stat() throws RecognitionException {
		HSQLTreeParser.insert_stat_return retval = new HSQLTreeParser.insert_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INSERT21=null;
		TreeRuleReturnScope tn =null;
		TreeRuleReturnScope vl =null;

		CommonTree INSERT21_tree=null;


		        retval.plan =new InsertRecordsPlan();
		    
		try {
			// HSQLTreeParser.g:183:5: ( ^( INSERT tn= table_name vl= literal_list ) )
			// HSQLTreeParser.g:184:5: ^( INSERT tn= table_name vl= literal_list )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			INSERT21=(CommonTree)match(input,INSERT,FOLLOW_INSERT_in_insert_stat911); 
			INSERT21_tree = (CommonTree)adaptor.dupNode(INSERT21);


			root_1 = (CommonTree)adaptor.becomeRoot(INSERT21_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_insert_stat915);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_literal_list_in_insert_stat919);
			vl=literal_list();
			state._fsp--;

			adaptor.addChild(root_1, vl.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			        retval.plan.setProperty("TABLENAME",(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			        retval.plan.setProperty("VALUELIST",(vl!=null?((HSQLTreeParser.literal_list_return)vl).valueList:null));
			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "insert_stat"


	public static class drop_stat_return extends TreeRuleReturnScope {
		public DropTablePlan plan;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "drop_stat"
	// HSQLTreeParser.g:190:1: drop_stat returns [DropTablePlan plan] : ^( DROP tn= table_name ) ;
	public final HSQLTreeParser.drop_stat_return drop_stat() throws RecognitionException {
		HSQLTreeParser.drop_stat_return retval = new HSQLTreeParser.drop_stat_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree DROP22=null;
		TreeRuleReturnScope tn =null;

		CommonTree DROP22_tree=null;


		        retval.plan =new DropTablePlan();
		    
		try {
			// HSQLTreeParser.g:195:5: ( ^( DROP tn= table_name ) )
			// HSQLTreeParser.g:195:6: ^( DROP tn= table_name )
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			{
			CommonTree _save_last_1 = _last;
			CommonTree _first_1 = null;
			CommonTree root_1 = (CommonTree)adaptor.nil();
			_last = (CommonTree)input.LT(1);
			DROP22=(CommonTree)match(input,DROP,FOLLOW_DROP_in_drop_stat957); 
			DROP22_tree = (CommonTree)adaptor.dupNode(DROP22);


			root_1 = (CommonTree)adaptor.becomeRoot(DROP22_tree, root_1);

			match(input, Token.DOWN, null); 
			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_table_name_in_drop_stat961);
			tn=table_name();
			state._fsp--;

			adaptor.addChild(root_1, tn.getTree());

			match(input, Token.UP, null); 
			adaptor.addChild(root_0, root_1);
			_last = _save_last_1;
			}



			      retval.plan.setProperty("TABLENAME",(tn!=null?((HSQLTreeParser.table_name_return)tn).tableName:null));
			    
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "drop_stat"


	public static class table_name_return extends TreeRuleReturnScope {
		public String tableName;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "table_name"
	// HSQLTreeParser.g:200:1: table_name returns [String tableName] : STR ;
	public final HSQLTreeParser.table_name_return table_name() throws RecognitionException {
		HSQLTreeParser.table_name_return retval = new HSQLTreeParser.table_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree STR23=null;

		CommonTree STR23_tree=null;

		try {
			// HSQLTreeParser.g:202:5: ( STR )
			// HSQLTreeParser.g:202:6: STR
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			STR23=(CommonTree)match(input,STR,FOLLOW_STR_in_table_name990); 
			STR23_tree = (CommonTree)adaptor.dupNode(STR23);


			adaptor.addChild(root_0, STR23_tree);

			retval.tableName =(STR23!=null?STR23.getText():null);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "table_name"


	public static class index_name_return extends TreeRuleReturnScope {
		public String indexName;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "index_name"
	// HSQLTreeParser.g:204:1: index_name returns [String indexName] : STR ;
	public final HSQLTreeParser.index_name_return index_name() throws RecognitionException {
		HSQLTreeParser.index_name_return retval = new HSQLTreeParser.index_name_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree STR24=null;

		CommonTree STR24_tree=null;

		try {
			// HSQLTreeParser.g:206:5: ( STR )
			// HSQLTreeParser.g:206:6: STR
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			STR24=(CommonTree)match(input,STR,FOLLOW_STR_in_index_name1015); 
			STR24_tree = (CommonTree)adaptor.dupNode(STR24);


			adaptor.addChild(root_0, STR24_tree);

			retval.indexName =(STR24!=null?STR24.getText():null);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "index_name"


	public static class column_withtype_list_return extends TreeRuleReturnScope {
		public List<ColumnRef> columns;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column_withtype_list"
	// HSQLTreeParser.g:208:1: column_withtype_list returns [List<ColumnRef> columns] : a= column_withtype ( COMMA !b= column_withtype )* ;
	public final HSQLTreeParser.column_withtype_list_return column_withtype_list() throws RecognitionException {
		HSQLTreeParser.column_withtype_list_return retval = new HSQLTreeParser.column_withtype_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COMMA25=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree COMMA25_tree=null;


		        retval.columns =new ArrayList<ColumnRef>();
		    
		try {
			// HSQLTreeParser.g:213:5: (a= column_withtype ( COMMA !b= column_withtype )* )
			// HSQLTreeParser.g:213:6: a= column_withtype ( COMMA !b= column_withtype )*
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_column_withtype_in_column_withtype_list1049);
			a=column_withtype();
			state._fsp--;

			adaptor.addChild(root_0, a.getTree());

			retval.columns.add((a!=null?((HSQLTreeParser.column_withtype_return)a).cr:null));
			// HSQLTreeParser.g:213:46: ( COMMA !b= column_withtype )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==COMMA) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// HSQLTreeParser.g:213:47: COMMA !b= column_withtype
					{
					_last = (CommonTree)input.LT(1);
					COMMA25=(CommonTree)match(input,COMMA,FOLLOW_COMMA_in_column_withtype_list1053); 

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_column_withtype_in_column_withtype_list1058);
					b=column_withtype();
					state._fsp--;

					adaptor.addChild(root_0, b.getTree());

					retval.columns.add((b!=null?((HSQLTreeParser.column_withtype_return)b).cr:null));
					}
					break;

				default :
					break loop12;
				}
			}

			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_withtype_list"


	public static class column_withtype_return extends TreeRuleReturnScope {
		public ColumnRef cr;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column_withtype"
	// HSQLTreeParser.g:215:1: column_withtype returns [ColumnRef cr] : n= column t= type ;
	public final HSQLTreeParser.column_withtype_return column_withtype() throws RecognitionException {
		HSQLTreeParser.column_withtype_return retval = new HSQLTreeParser.column_withtype_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		TreeRuleReturnScope n =null;
		TreeRuleReturnScope t =null;


		try {
			// HSQLTreeParser.g:217:5: (n= column t= type )
			// HSQLTreeParser.g:217:6: n= column t= type
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_column_in_column_withtype1085);
			n=column();
			state._fsp--;

			adaptor.addChild(root_0, n.getTree());

			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_type_in_column_withtype1089);
			t=type();
			state._fsp--;

			adaptor.addChild(root_0, t.getTree());

			retval.cr =new ColumnRef((n!=null?((HSQLTreeParser.column_return)n).name:null),(t!=null?((HSQLTreeParser.type_return)t).t:0));
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column_withtype"


	public static class column_return extends TreeRuleReturnScope {
		public String name;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "column"
	// HSQLTreeParser.g:220:1: column returns [String name] : STR ;
	public final HSQLTreeParser.column_return column() throws RecognitionException {
		HSQLTreeParser.column_return retval = new HSQLTreeParser.column_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree STR26=null;

		CommonTree STR26_tree=null;

		try {
			// HSQLTreeParser.g:222:5: ( STR )
			// HSQLTreeParser.g:222:6: STR
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			STR26=(CommonTree)match(input,STR,FOLLOW_STR_in_column1117); 
			STR26_tree = (CommonTree)adaptor.dupNode(STR26);


			adaptor.addChild(root_0, STR26_tree);

			retval.name =(STR26!=null?STR26.getText():null);
			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "column"


	public static class type_return extends TreeRuleReturnScope {
		public int t;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "type"
	// HSQLTreeParser.g:224:1: type returns [int t] : ( INT | STRING | LONG | DOUBLE );
	public final HSQLTreeParser.type_return type() throws RecognitionException {
		HSQLTreeParser.type_return retval = new HSQLTreeParser.type_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree INT27=null;
		CommonTree STRING28=null;
		CommonTree LONG29=null;
		CommonTree DOUBLE30=null;

		CommonTree INT27_tree=null;
		CommonTree STRING28_tree=null;
		CommonTree LONG29_tree=null;
		CommonTree DOUBLE30_tree=null;

		try {
			// HSQLTreeParser.g:226:5: ( INT | STRING | LONG | DOUBLE )
			int alt13=4;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt13=1;
				}
				break;
			case STRING:
				{
				alt13=2;
				}
				break;
			case LONG:
				{
				alt13=3;
				}
				break;
			case DOUBLE:
				{
				alt13=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// HSQLTreeParser.g:226:6: INT
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					INT27=(CommonTree)match(input,INT,FOLLOW_INT_in_type1141); 
					INT27_tree = (CommonTree)adaptor.dupNode(INT27);


					adaptor.addChild(root_0, INT27_tree);

					retval.t =ColumnRef.Type.INT.ordinal();
					}
					break;
				case 2 :
					// HSQLTreeParser.g:227:6: STRING
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					STRING28=(CommonTree)match(input,STRING,FOLLOW_STRING_in_type1149); 
					STRING28_tree = (CommonTree)adaptor.dupNode(STRING28);


					adaptor.addChild(root_0, STRING28_tree);

					retval.t =ColumnRef.Type.STRING.ordinal();
					}
					break;
				case 3 :
					// HSQLTreeParser.g:228:6: LONG
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					LONG29=(CommonTree)match(input,LONG,FOLLOW_LONG_in_type1158); 
					LONG29_tree = (CommonTree)adaptor.dupNode(LONG29);


					adaptor.addChild(root_0, LONG29_tree);

					retval.t =ColumnRef.Type.LONG.ordinal();
					}
					break;
				case 4 :
					// HSQLTreeParser.g:229:6: DOUBLE
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					DOUBLE30=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_type1167); 
					DOUBLE30_tree = (CommonTree)adaptor.dupNode(DOUBLE30);


					adaptor.addChild(root_0, DOUBLE30_tree);

					retval.t =ColumnRef.Type.DOUBLE.ordinal();
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "type"


	public static class literals_return extends TreeRuleReturnScope {
		public ValueRef v;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "literals"
	// HSQLTreeParser.g:231:1: literals returns [ValueRef v] : (i= INTEGER_LITERAL |l= LONG_LITERAL |d= DOUBLE_LITERAL |s= STRING_LITERAL );
	public final HSQLTreeParser.literals_return literals() throws RecognitionException {
		HSQLTreeParser.literals_return retval = new HSQLTreeParser.literals_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree i=null;
		CommonTree l=null;
		CommonTree d=null;
		CommonTree s=null;

		CommonTree i_tree=null;
		CommonTree l_tree=null;
		CommonTree d_tree=null;
		CommonTree s_tree=null;

		try {
			// HSQLTreeParser.g:233:5: (i= INTEGER_LITERAL |l= LONG_LITERAL |d= DOUBLE_LITERAL |s= STRING_LITERAL )
			int alt14=4;
			switch ( input.LA(1) ) {
			case INTEGER_LITERAL:
				{
				alt14=1;
				}
				break;
			case LONG_LITERAL:
				{
				alt14=2;
				}
				break;
			case DOUBLE_LITERAL:
				{
				alt14=3;
				}
				break;
			case STRING_LITERAL:
				{
				alt14=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 14, 0, input);
				throw nvae;
			}
			switch (alt14) {
				case 1 :
					// HSQLTreeParser.g:234:6: i= INTEGER_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					i=(CommonTree)match(input,INTEGER_LITERAL,FOLLOW_INTEGER_LITERAL_in_literals1199); 
					i_tree = (CommonTree)adaptor.dupNode(i);


					adaptor.addChild(root_0, i_tree);

					retval.v =new ValueRef().setValue(Integer.valueOf((i!=null?i.getText():null)));
					}
					break;
				case 2 :
					// HSQLTreeParser.g:235:6: l= LONG_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					l=(CommonTree)match(input,LONG_LITERAL,FOLLOW_LONG_LITERAL_in_literals1210); 
					l_tree = (CommonTree)adaptor.dupNode(l);


					adaptor.addChild(root_0, l_tree);

					retval.v =new ValueRef().setValue(Long.valueOf((l!=null?l.getText():null)));
					}
					break;
				case 3 :
					// HSQLTreeParser.g:236:6: d= DOUBLE_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					d=(CommonTree)match(input,DOUBLE_LITERAL,FOLLOW_DOUBLE_LITERAL_in_literals1224); 
					d_tree = (CommonTree)adaptor.dupNode(d);


					adaptor.addChild(root_0, d_tree);

					retval.v =new ValueRef().setValue(Double.valueOf((d!=null?d.getText():null)));
					}
					break;
				case 4 :
					// HSQLTreeParser.g:237:6: s= STRING_LITERAL
					{
					root_0 = (CommonTree)adaptor.nil();


					_last = (CommonTree)input.LT(1);
					s=(CommonTree)match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_literals1236); 
					s_tree = (CommonTree)adaptor.dupNode(s);


					adaptor.addChild(root_0, s_tree);

					retval.v =new ValueRef().setValue((s!=null?s.getText():null));
					}
					break;

			}
			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literals"


	public static class literal_list_return extends TreeRuleReturnScope {
		public List<ValueRef> valueList;
		CommonTree tree;
		@Override
		public CommonTree getTree() { return tree; }
	};


	// $ANTLR start "literal_list"
	// HSQLTreeParser.g:239:1: literal_list returns [List<ValueRef> valueList] : a= literals ( COMMA b= literals )* ;
	public final HSQLTreeParser.literal_list_return literal_list() throws RecognitionException {
		HSQLTreeParser.literal_list_return retval = new HSQLTreeParser.literal_list_return();
		retval.start = input.LT(1);

		CommonTree root_0 = null;

		CommonTree _first_0 = null;
		CommonTree _last = null;


		CommonTree COMMA31=null;
		TreeRuleReturnScope a =null;
		TreeRuleReturnScope b =null;

		CommonTree COMMA31_tree=null;


		        retval.valueList =new ArrayList<ValueRef>();
		    
		try {
			// HSQLTreeParser.g:244:5: (a= literals ( COMMA b= literals )* )
			// HSQLTreeParser.g:244:6: a= literals ( COMMA b= literals )*
			{
			root_0 = (CommonTree)adaptor.nil();


			_last = (CommonTree)input.LT(1);
			pushFollow(FOLLOW_literals_in_literal_list1271);
			a=literals();
			state._fsp--;

			adaptor.addChild(root_0, a.getTree());

			retval.valueList.add((a!=null?((HSQLTreeParser.literals_return)a).v:null));
			// HSQLTreeParser.g:244:39: ( COMMA b= literals )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( (LA15_0==COMMA) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// HSQLTreeParser.g:244:40: COMMA b= literals
					{
					_last = (CommonTree)input.LT(1);
					COMMA31=(CommonTree)match(input,COMMA,FOLLOW_COMMA_in_literal_list1274); 
					COMMA31_tree = (CommonTree)adaptor.dupNode(COMMA31);


					adaptor.addChild(root_0, COMMA31_tree);

					_last = (CommonTree)input.LT(1);
					pushFollow(FOLLOW_literals_in_literal_list1278);
					b=literals();
					state._fsp--;

					adaptor.addChild(root_0, b.getTree());

					retval.valueList.add((b!=null?((HSQLTreeParser.literals_return)b).v:null));
					}
					break;

				default :
					break loop15;
				}
			}

			}

			retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "literal_list"

	// Delegated rules



	public static final BitSet FOLLOW_create_stat_in_sql_stat53 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_drop_stat_in_sql_stat64 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_insert_stat_in_sql_stat75 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_stat_in_sql_stat86 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_delete_stat_in_sql_stat97 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_update_stat_in_sql_stat108 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_create_index_in_sql_stat119 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INDEX_in_create_index152 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_index_name_in_create_index156 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_create_index160 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_UPDATE_in_update_stat203 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_name_in_update_stat207 = new BitSet(new long[]{0x0000020000000000L});
	public static final BitSet FOLLOW_update_list_in_update_stat211 = new BitSet(new long[]{0x0004000000000008L});
	public static final BitSet FOLLOW_where_clause_in_update_stat215 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SET_in_update_list259 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_update_element_in_update_list263 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_update_element_in_update_list269 = new BitSet(new long[]{0x0000000000040008L});
	public static final BitSet FOLLOW_EQ_in_update_element303 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_column_in_update_element307 = new BitSet(new long[]{0x0000100022008000L});
	public static final BitSet FOLLOW_expression_in_update_element312 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_literals_in_expression343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHERE_in_where_clause368 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_andCondition_in_where_clause372 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_orCondition_in_andCondition406 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_AND_in_andCondition410 = new BitSet(new long[]{0x00000005C0340000L});
	public static final BitSet FOLLOW_orCondition_in_andCondition415 = new BitSet(new long[]{0x0000000000000012L});
	public static final BitSet FOLLOW_atomCondition_in_orCondition451 = new BitSet(new long[]{0x0000001000000002L});
	public static final BitSet FOLLOW_OR_in_orCondition456 = new BitSet(new long[]{0x00000005C0340000L});
	public static final BitSet FOLLOW_atomCondition_in_orCondition461 = new BitSet(new long[]{0x0000001000000002L});
	public static final BitSet FOLLOW_comp_op_in_atomCondition491 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_column_in_atomCondition495 = new BitSet(new long[]{0x0000100022008000L});
	public static final BitSet FOLLOW_expression_in_atomCondition499 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_L_PAREN_in_atomCondition519 = new BitSet(new long[]{0x00000005C0340000L});
	public static final BitSet FOLLOW_andCondition_in_atomCondition523 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_R_AREN_in_atomCondition525 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_delete_stat558 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_name_in_delete_stat562 = new BitSet(new long[]{0x0004000000000008L});
	public static final BitSet FOLLOW_where_clause_in_delete_stat566 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_EQ_in_comp_op603 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEQ_in_comp_op612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LT_in_comp_op621 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LTE_in_comp_op631 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GT_in_comp_op640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GTE_in_comp_op650 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SELECT_in_select_stat684 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_select_list_in_select_stat688 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_table_name_in_select_stat692 = new BitSet(new long[]{0x0004000000000008L});
	public static final BitSet FOLLOW_where_clause_in_select_stat697 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SELECT_LIST_in_select_list745 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_projector_in_select_list750 = new BitSet(new long[]{0x0000440000000008L});
	public static final BitSet FOLLOW_column_in_projector785 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fun_in_projector796 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_column_in_projector800 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_SUM_in_fun825 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CREATE_in_create_stat859 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_name_in_create_stat863 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_column_withtype_list_in_create_stat867 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_INSERT_in_insert_stat911 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_name_in_insert_stat915 = new BitSet(new long[]{0x0000100022008000L});
	public static final BitSet FOLLOW_literal_list_in_insert_stat919 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_DROP_in_drop_stat957 = new BitSet(new long[]{0x0000000000000004L});
	public static final BitSet FOLLOW_table_name_in_drop_stat961 = new BitSet(new long[]{0x0000000000000008L});
	public static final BitSet FOLLOW_STR_in_table_name990 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STR_in_index_name1015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_column_withtype_in_column_withtype_list1049 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_column_withtype_list1053 = new BitSet(new long[]{0x0000040000000000L});
	public static final BitSet FOLLOW_column_withtype_in_column_withtype_list1058 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_column_in_column_withtype1085 = new BitSet(new long[]{0x0000080011004000L});
	public static final BitSet FOLLOW_type_in_column_withtype1089 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STR_in_column1117 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_type1141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_type1149 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_in_type1158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_in_type1167 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INTEGER_LITERAL_in_literals1199 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONG_LITERAL_in_literals1210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLE_LITERAL_in_literals1224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_literals1236 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_literals_in_literal_list1271 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_COMMA_in_literal_list1274 = new BitSet(new long[]{0x0000100022008000L});
	public static final BitSet FOLLOW_literals_in_literal_list1278 = new BitSet(new long[]{0x0000000000000082L});
}
