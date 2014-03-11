// $ANTLR 3.5 HSQLLexer.g 2014-01-14 22:28:46

package org.ict.hsql.parse;
import org.ict.hsql.schema.*;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class HSQLLexer extends Lexer {
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

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public HSQLLexer() {} 
	public HSQLLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public HSQLLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "HSQLLexer.g"; }

	// $ANTLR start "SUM"
	public final void mSUM() throws RecognitionException {
		try {
			int _type = SUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:6:4: ( 'SUM' )
			// HSQLLexer.g:6:5: 'SUM'
			{
			match("SUM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SUM"

	// $ANTLR start "CREATE"
	public final void mCREATE() throws RecognitionException {
		try {
			int _type = CREATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:7:7: ( 'CREATE' )
			// HSQLLexer.g:7:8: 'CREATE'
			{
			match("CREATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CREATE"

	// $ANTLR start "ON"
	public final void mON() throws RecognitionException {
		try {
			int _type = ON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:8:3: ( 'ON' )
			// HSQLLexer.g:8:4: 'ON'
			{
			match("ON"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ON"

	// $ANTLR start "INDEX"
	public final void mINDEX() throws RecognitionException {
		try {
			int _type = INDEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:9:6: ( 'INDEX' )
			// HSQLLexer.g:9:7: 'INDEX'
			{
			match("INDEX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INDEX"

	// $ANTLR start "DELETE"
	public final void mDELETE() throws RecognitionException {
		try {
			int _type = DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:10:7: ( 'DELETE' )
			// HSQLLexer.g:10:8: 'DELETE'
			{
			match("DELETE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE"

	// $ANTLR start "WHERE"
	public final void mWHERE() throws RecognitionException {
		try {
			int _type = WHERE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:11:6: ( 'WHERE' )
			// HSQLLexer.g:11:7: 'WHERE'
			{
			match("WHERE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHERE"

	// $ANTLR start "SELECT"
	public final void mSELECT() throws RecognitionException {
		try {
			int _type = SELECT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:12:7: ( 'SELECT' )
			// HSQLLexer.g:12:8: 'SELECT'
			{
			match("SELECT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SELECT"

	// $ANTLR start "FROM"
	public final void mFROM() throws RecognitionException {
		try {
			int _type = FROM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:13:5: ( 'FROM' )
			// HSQLLexer.g:13:6: 'FROM'
			{
			match("FROM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FROM"

	// $ANTLR start "DROP"
	public final void mDROP() throws RecognitionException {
		try {
			int _type = DROP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:14:5: ( 'DROP' )
			// HSQLLexer.g:14:6: 'DROP'
			{
			match("DROP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DROP"

	// $ANTLR start "INSERT"
	public final void mINSERT() throws RecognitionException {
		try {
			int _type = INSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:15:7: ( 'INSERT' )
			// HSQLLexer.g:15:8: 'INSERT'
			{
			match("INSERT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INSERT"

	// $ANTLR start "UPDATE"
	public final void mUPDATE() throws RecognitionException {
		try {
			int _type = UPDATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:16:7: ( 'UPDATE' )
			// HSQLLexer.g:16:8: 'UPDATE'
			{
			match("UPDATE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "UPDATE"

	// $ANTLR start "INTO"
	public final void mINTO() throws RecognitionException {
		try {
			int _type = INTO;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:17:5: ( 'INTO' )
			// HSQLLexer.g:17:6: 'INTO'
			{
			match("INTO"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTO"

	// $ANTLR start "VALUES"
	public final void mVALUES() throws RecognitionException {
		try {
			int _type = VALUES;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:18:7: ( 'VALUES' )
			// HSQLLexer.g:18:8: 'VALUES'
			{
			match("VALUES"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VALUES"

	// $ANTLR start "TABLE"
	public final void mTABLE() throws RecognitionException {
		try {
			int _type = TABLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:19:6: ( 'TABLE' )
			// HSQLLexer.g:19:7: 'TABLE'
			{
			match("TABLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TABLE"

	// $ANTLR start "SET"
	public final void mSET() throws RecognitionException {
		try {
			int _type = SET;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:20:4: ( 'SET' )
			// HSQLLexer.g:20:5: 'SET'
			{
			match("SET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SET"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:21:3: ( 'OR' )
			// HSQLLexer.g:21:4: 'OR'
			{
			match("OR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:22:4: ( 'AND' )
			// HSQLLexer.g:22:5: 'AND'
			{
			match("AND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "PRIMARYKEY"
	public final void mPRIMARYKEY() throws RecognitionException {
		try {
			int _type = PRIMARYKEY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:23:11: ( 'PRIMARY KEY' )
			// HSQLLexer.g:23:12: 'PRIMARY KEY'
			{
			match("PRIMARY KEY"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIMARYKEY"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:24:4: ( 'INT' )
			// HSQLLexer.g:24:5: 'INT'
			{
			match("INT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:25:7: ( 'STRING' )
			// HSQLLexer.g:25:8: 'STRING'
			{
			match("STRING"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:26:7: ( 'DOUBLE' )
			// HSQLLexer.g:26:8: 'DOUBLE'
			{
			match("DOUBLE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "LONG"
	public final void mLONG() throws RecognitionException {
		try {
			int _type = LONG;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:27:5: ( 'LONG' )
			// HSQLLexer.g:27:6: 'LONG'
			{
			match("LONG"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG"

	// $ANTLR start "STR"
	public final void mSTR() throws RecognitionException {
		try {
			int _type = STR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:28:4: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | ':' | '0' .. '9' )* )
			// HSQLLexer.g:28:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | ':' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// HSQLLexer.g:28:28: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | ':' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= ':')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= ':')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STR"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:29:3: ( ( ' ' | '\\t' )+ )
			// HSQLLexer.g:29:4: ( ' ' | '\\t' )+
			{
			// HSQLLexer.g:29:4: ( ' ' | '\\t' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0=='\t'||LA2_0==' ') ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "L_PAREN"
	public final void mL_PAREN() throws RecognitionException {
		try {
			int _type = L_PAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:30:8: ( '(' )
			// HSQLLexer.g:30:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "L_PAREN"

	// $ANTLR start "R_PAREN"
	public final void mR_PAREN() throws RecognitionException {
		try {
			int _type = R_PAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:31:8: ( ')' )
			// HSQLLexer.g:31:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "R_PAREN"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:32:6: ( ',' )
			// HSQLLexer.g:32:7: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:33:6: ( '-' )
			// HSQLLexer.g:33:7: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "STR_LITERALS"
	public final void mSTR_LITERALS() throws RecognitionException {
		try {
			int _type = STR_LITERALS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:35:13: ( '\\'' STR )
			// HSQLLexer.g:35:14: '\\'' STR
			{
			match('\''); 
			mSTR(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STR_LITERALS"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// HSQLLexer.g:38:5: ( 'a' .. 'z' | 'A' .. 'Z' )
			// HSQLLexer.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			CommonToken t=null;

			 StringBuilder sb = new StringBuilder(); 
			// HSQLLexer.g:45:5: ( '\\'' (t= CHAR |t= CHAR_ESC )* '\\'' )
			// HSQLLexer.g:45:9: '\\'' (t= CHAR |t= CHAR_ESC )* '\\''
			{
			match('\''); 
			// HSQLLexer.g:46:5: (t= CHAR |t= CHAR_ESC )*
			loop3:
			while (true) {
				int alt3=3;
				int LA3_0 = input.LA(1);
				if ( (LA3_0=='\'') ) {
					int LA3_1 = input.LA(2);
					if ( (LA3_1=='\'') ) {
						alt3=2;
					}

				}
				else if ( ((LA3_0 >= '\u0000' && LA3_0 <= '&')||(LA3_0 >= '(' && LA3_0 <= '[')||(LA3_0 >= ']' && LA3_0 <= '\uFFFF')) ) {
					alt3=1;
				}
				else if ( (LA3_0=='\\') ) {
					alt3=2;
				}

				switch (alt3) {
				case 1 :
					// HSQLLexer.g:46:7: t= CHAR
					{
					int tStart255 = getCharIndex();
					int tStartLine255 = getLine();
					int tStartCharPos255 = getCharPositionInLine();
					mCHAR(); 
					t = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, tStart255, getCharIndex()-1);
					t.setLine(tStartLine255);
					t.setCharPositionInLine(tStartCharPos255);

					 sb.append(t.getText()); 
					}
					break;
				case 2 :
					// HSQLLexer.g:47:7: t= CHAR_ESC
					{
					int tStart267 = getCharIndex();
					int tStartLine267 = getLine();
					int tStartCharPos267 = getCharPositionInLine();
					mCHAR_ESC(); 
					t = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, tStart267, getCharIndex()-1);
					t.setLine(tStartLine267);
					t.setCharPositionInLine(tStartCharPos267);

					 sb.append(getText()); 
					}
					break;

				default :
					break loop3;
				}
			}

			match('\''); 
			 setText(sb.toString()); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "CHAR"
	public final void mCHAR() throws RecognitionException {
		try {
			// HSQLLexer.g:53:5: ( (~ ( '\\'' | '\\\\' ) )+ )
			// HSQLLexer.g:53:9: (~ ( '\\'' | '\\\\' ) )+
			{
			// HSQLLexer.g:53:9: (~ ( '\\'' | '\\\\' ) )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\u0000' && LA4_0 <= '&')||(LA4_0 >= '(' && LA4_0 <= '[')||(LA4_0 >= ']' && LA4_0 <= '\uFFFF')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR"

	// $ANTLR start "DBL_QUOTE_CHAR"
	public final void mDBL_QUOTE_CHAR() throws RecognitionException {
		try {
			// HSQLLexer.g:58:5: ( (~ ( '\\\"' ) )+ )
			// HSQLLexer.g:58:9: (~ ( '\\\"' ) )+
			{
			// HSQLLexer.g:58:9: (~ ( '\\\"' ) )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '\u0000' && LA5_0 <= '!')||(LA5_0 >= '#' && LA5_0 <= '\uFFFF')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DBL_QUOTE_CHAR"

	// $ANTLR start "CHAR_ESC"
	public final void mCHAR_ESC() throws RecognitionException {
		try {
			// HSQLLexer.g:64:5: ( '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\\\"' | '\\'' | '\\\\' | '_' | '%' ) | '\\'\\'' )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0=='\\') ) {
				alt7=1;
			}
			else if ( (LA7_0=='\'') ) {
				alt7=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// HSQLLexer.g:64:9: '\\\\' ( 'n' | 'r' | 't' | 'b' | 'f' | '\\\"' | '\\'' | '\\\\' | '_' | '%' )
					{
					match('\\'); 
					// HSQLLexer.g:65:9: ( 'n' | 'r' | 't' | 'b' | 'f' | '\\\"' | '\\'' | '\\\\' | '_' | '%' )
					int alt6=10;
					switch ( input.LA(1) ) {
					case 'n':
						{
						alt6=1;
						}
						break;
					case 'r':
						{
						alt6=2;
						}
						break;
					case 't':
						{
						alt6=3;
						}
						break;
					case 'b':
						{
						alt6=4;
						}
						break;
					case 'f':
						{
						alt6=5;
						}
						break;
					case '\"':
						{
						alt6=6;
						}
						break;
					case '\'':
						{
						alt6=7;
						}
						break;
					case '\\':
						{
						alt6=8;
						}
						break;
					case '_':
						{
						alt6=9;
						}
						break;
					case '%':
						{
						alt6=10;
						}
						break;
					default:
						NoViableAltException nvae =
							new NoViableAltException("", 6, 0, input);
						throw nvae;
					}
					switch (alt6) {
						case 1 :
							// HSQLLexer.g:65:11: 'n'
							{
							match('n'); 
							 setText("\n"); 
							}
							break;
						case 2 :
							// HSQLLexer.g:66:11: 'r'
							{
							match('r'); 
							 setText("\r"); 
							}
							break;
						case 3 :
							// HSQLLexer.g:67:11: 't'
							{
							match('t'); 
							 setText("\t"); 
							}
							break;
						case 4 :
							// HSQLLexer.g:68:11: 'b'
							{
							match('b'); 
							 setText("\b"); 
							}
							break;
						case 5 :
							// HSQLLexer.g:69:11: 'f'
							{
							match('f'); 
							 setText("\f"); 
							}
							break;
						case 6 :
							// HSQLLexer.g:70:11: '\\\"'
							{
							match('\"'); 
							 setText("\""); 
							}
							break;
						case 7 :
							// HSQLLexer.g:71:11: '\\''
							{
							match('\''); 
							 setText("\'"); 
							}
							break;
						case 8 :
							// HSQLLexer.g:72:11: '\\\\'
							{
							match('\\'); 
							 setText("\\"); 
							}
							break;
						case 9 :
							// HSQLLexer.g:73:11: '_'
							{
							match('_'); 
							 setText("\\_"); 
							}
							break;
						case 10 :
							// HSQLLexer.g:74:11: '%'
							{
							match('%'); 
							 setText("\\%"); 
							}
							break;

					}

					}
					break;
				case 2 :
					// HSQLLexer.g:76:9: '\\'\\''
					{
					match("''"); 

					 setText("\'"); 
					}
					break;

			}
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR_ESC"

	// $ANTLR start "NEQ"
	public final void mNEQ() throws RecognitionException {
		try {
			int _type = NEQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:79:5: ( '!=' )
			// HSQLLexer.g:79:6: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEQ"

	// $ANTLR start "EQ"
	public final void mEQ() throws RecognitionException {
		try {
			int _type = EQ;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:82:5: ( '=' )
			// HSQLLexer.g:82:7: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQ"

	// $ANTLR start "LT"
	public final void mLT() throws RecognitionException {
		try {
			int _type = LT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:85:5: ( '<' )
			// HSQLLexer.g:85:6: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LT"

	// $ANTLR start "LTE"
	public final void mLTE() throws RecognitionException {
		try {
			int _type = LTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:88:5: ( '<=' )
			// HSQLLexer.g:88:6: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LTE"

	// $ANTLR start "GT"
	public final void mGT() throws RecognitionException {
		try {
			int _type = GT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:91:5: ( '>' )
			// HSQLLexer.g:91:6: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GT"

	// $ANTLR start "GTE"
	public final void mGTE() throws RecognitionException {
		try {
			int _type = GTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:94:5: ( '>=' )
			// HSQLLexer.g:94:6: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GTE"

	// $ANTLR start "EOL"
	public final void mEOL() throws RecognitionException {
		try {
			int _type = EOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:98:5: ( ( '\\r' | '\\n' ) )
			// HSQLLexer.g:98:8: ( '\\r' | '\\n' )
			{
			if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EOL"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:103:5: ( '.' )
			// HSQLLexer.g:103:7: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "POSINTEGER"
	public final void mPOSINTEGER() throws RecognitionException {
		try {
			// HSQLLexer.g:109:5: ( ( DIGIT )+ )
			// HSQLLexer.g:109:9: ( DIGIT )+
			{
			// HSQLLexer.g:109:9: ( DIGIT )+
			int cnt8=0;
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= '0' && LA8_0 <= '9')) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt8 >= 1 ) break loop8;
					EarlyExitException eee = new EarlyExitException(8, input);
					throw eee;
				}
				cnt8++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "POSINTEGER"

	// $ANTLR start "DIGIT"
	public final void mDIGIT() throws RecognitionException {
		try {
			// HSQLLexer.g:113:5: ( '0' .. '9' )
			// HSQLLexer.g:
			{
			if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGIT"

	// $ANTLR start "INTEGER_LITERAL"
	public final void mINTEGER_LITERAL() throws RecognitionException {
		try {
			int _type = INTEGER_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:116:5: ( ( MINUS )? POSINTEGER )
			// HSQLLexer.g:116:6: ( MINUS )? POSINTEGER
			{
			// HSQLLexer.g:116:6: ( MINUS )?
			int alt9=2;
			int LA9_0 = input.LA(1);
			if ( (LA9_0=='-') ) {
				alt9=1;
			}
			switch (alt9) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			mPOSINTEGER(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INTEGER_LITERAL"

	// $ANTLR start "LONG_LITERAL"
	public final void mLONG_LITERAL() throws RecognitionException {
		try {
			int _type = LONG_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:120:5: ( ( MINUS )? POSINTEGER ( 'L' | 'l' ) )
			// HSQLLexer.g:120:6: ( MINUS )? POSINTEGER ( 'L' | 'l' )
			{
			// HSQLLexer.g:120:6: ( MINUS )?
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0=='-') ) {
				alt10=1;
			}
			switch (alt10) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			mPOSINTEGER(); 

			if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONG_LITERAL"

	// $ANTLR start "DOUBLE_LITERAL"
	public final void mDOUBLE_LITERAL() throws RecognitionException {
		try {
			int _type = DOUBLE_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// HSQLLexer.g:123:5: ( ( MINUS )? DECIMAL ( 'D' | 'd' )? )
			// HSQLLexer.g:123:6: ( MINUS )? DECIMAL ( 'D' | 'd' )?
			{
			// HSQLLexer.g:123:6: ( MINUS )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0=='-') ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			mDECIMAL(); 

			// HSQLLexer.g:123:20: ( 'D' | 'd' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0=='D'||LA12_0=='d') ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// HSQLLexer.g:
					{
					if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE_LITERAL"

	// $ANTLR start "DECIMAL"
	public final void mDECIMAL() throws RecognitionException {
		try {
			// HSQLLexer.g:128:5: ( ( POSINTEGER )? '.' POSINTEGER )
			// HSQLLexer.g:128:9: ( POSINTEGER )? '.' POSINTEGER
			{
			// HSQLLexer.g:128:9: ( POSINTEGER )?
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( ((LA13_0 >= '0' && LA13_0 <= '9')) ) {
				alt13=1;
			}
			switch (alt13) {
				case 1 :
					// HSQLLexer.g:128:9: POSINTEGER
					{
					mPOSINTEGER(); 

					}
					break;

			}

			match('.'); 
			mPOSINTEGER(); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DECIMAL"

	@Override
	public void mTokens() throws RecognitionException {
		// HSQLLexer.g:1:8: ( SUM | CREATE | ON | INDEX | DELETE | WHERE | SELECT | FROM | DROP | INSERT | UPDATE | INTO | VALUES | TABLE | SET | OR | AND | PRIMARYKEY | INT | STRING | DOUBLE | LONG | STR | WS | L_PAREN | R_PAREN | COMMA | MINUS | STR_LITERALS | STRING_LITERAL | NEQ | EQ | LT | LTE | GT | GTE | EOL | DOT | INTEGER_LITERAL | LONG_LITERAL | DOUBLE_LITERAL )
		int alt14=41;
		alt14 = dfa14.predict(input);
		switch (alt14) {
			case 1 :
				// HSQLLexer.g:1:10: SUM
				{
				mSUM(); 

				}
				break;
			case 2 :
				// HSQLLexer.g:1:14: CREATE
				{
				mCREATE(); 

				}
				break;
			case 3 :
				// HSQLLexer.g:1:21: ON
				{
				mON(); 

				}
				break;
			case 4 :
				// HSQLLexer.g:1:24: INDEX
				{
				mINDEX(); 

				}
				break;
			case 5 :
				// HSQLLexer.g:1:30: DELETE
				{
				mDELETE(); 

				}
				break;
			case 6 :
				// HSQLLexer.g:1:37: WHERE
				{
				mWHERE(); 

				}
				break;
			case 7 :
				// HSQLLexer.g:1:43: SELECT
				{
				mSELECT(); 

				}
				break;
			case 8 :
				// HSQLLexer.g:1:50: FROM
				{
				mFROM(); 

				}
				break;
			case 9 :
				// HSQLLexer.g:1:55: DROP
				{
				mDROP(); 

				}
				break;
			case 10 :
				// HSQLLexer.g:1:60: INSERT
				{
				mINSERT(); 

				}
				break;
			case 11 :
				// HSQLLexer.g:1:67: UPDATE
				{
				mUPDATE(); 

				}
				break;
			case 12 :
				// HSQLLexer.g:1:74: INTO
				{
				mINTO(); 

				}
				break;
			case 13 :
				// HSQLLexer.g:1:79: VALUES
				{
				mVALUES(); 

				}
				break;
			case 14 :
				// HSQLLexer.g:1:86: TABLE
				{
				mTABLE(); 

				}
				break;
			case 15 :
				// HSQLLexer.g:1:92: SET
				{
				mSET(); 

				}
				break;
			case 16 :
				// HSQLLexer.g:1:96: OR
				{
				mOR(); 

				}
				break;
			case 17 :
				// HSQLLexer.g:1:99: AND
				{
				mAND(); 

				}
				break;
			case 18 :
				// HSQLLexer.g:1:103: PRIMARYKEY
				{
				mPRIMARYKEY(); 

				}
				break;
			case 19 :
				// HSQLLexer.g:1:114: INT
				{
				mINT(); 

				}
				break;
			case 20 :
				// HSQLLexer.g:1:118: STRING
				{
				mSTRING(); 

				}
				break;
			case 21 :
				// HSQLLexer.g:1:125: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 22 :
				// HSQLLexer.g:1:132: LONG
				{
				mLONG(); 

				}
				break;
			case 23 :
				// HSQLLexer.g:1:137: STR
				{
				mSTR(); 

				}
				break;
			case 24 :
				// HSQLLexer.g:1:141: WS
				{
				mWS(); 

				}
				break;
			case 25 :
				// HSQLLexer.g:1:144: L_PAREN
				{
				mL_PAREN(); 

				}
				break;
			case 26 :
				// HSQLLexer.g:1:152: R_PAREN
				{
				mR_PAREN(); 

				}
				break;
			case 27 :
				// HSQLLexer.g:1:160: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 28 :
				// HSQLLexer.g:1:166: MINUS
				{
				mMINUS(); 

				}
				break;
			case 29 :
				// HSQLLexer.g:1:172: STR_LITERALS
				{
				mSTR_LITERALS(); 

				}
				break;
			case 30 :
				// HSQLLexer.g:1:185: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 31 :
				// HSQLLexer.g:1:200: NEQ
				{
				mNEQ(); 

				}
				break;
			case 32 :
				// HSQLLexer.g:1:204: EQ
				{
				mEQ(); 

				}
				break;
			case 33 :
				// HSQLLexer.g:1:207: LT
				{
				mLT(); 

				}
				break;
			case 34 :
				// HSQLLexer.g:1:210: LTE
				{
				mLTE(); 

				}
				break;
			case 35 :
				// HSQLLexer.g:1:214: GT
				{
				mGT(); 

				}
				break;
			case 36 :
				// HSQLLexer.g:1:217: GTE
				{
				mGTE(); 

				}
				break;
			case 37 :
				// HSQLLexer.g:1:221: EOL
				{
				mEOL(); 

				}
				break;
			case 38 :
				// HSQLLexer.g:1:225: DOT
				{
				mDOT(); 

				}
				break;
			case 39 :
				// HSQLLexer.g:1:229: INTEGER_LITERAL
				{
				mINTEGER_LITERAL(); 

				}
				break;
			case 40 :
				// HSQLLexer.g:1:245: LONG_LITERAL
				{
				mLONG_LITERAL(); 

				}
				break;
			case 41 :
				// HSQLLexer.g:1:258: DOUBLE_LITERAL
				{
				mDOUBLE_LITERAL(); 

				}
				break;

		}
	}


	protected DFA14 dfa14 = new DFA14(this);
	static final String DFA14_eotS =
		"\1\uffff\15\16\5\uffff\1\56\3\uffff\1\63\1\65\1\uffff\1\66\1\67\4\16\1"+
		"\76\1\77\14\16\2\uffff\1\117\10\uffff\1\120\1\16\1\122\2\16\2\uffff\2"+
		"\16\1\130\10\16\1\141\2\16\1\117\2\uffff\1\16\1\uffff\4\16\1\151\1\uffff"+
		"\1\16\1\153\2\16\1\156\3\16\1\uffff\1\16\1\163\3\16\1\167\1\16\1\uffff"+
		"\1\16\1\uffff\1\16\1\173\1\uffff\2\16\1\176\1\16\1\uffff\1\u0080\1\u0081"+
		"\1\u0082\1\uffff\1\u0083\1\u0084\1\u0085\1\uffff\1\u0086\1\u0087\1\uffff"+
		"\1\16\10\uffff\1\16\1\uffff";
	static final String DFA14_eofS =
		"\u008a\uffff";
	static final String DFA14_minS =
		"\1\11\1\105\1\122\2\116\1\105\1\110\1\122\1\120\2\101\1\116\1\122\1\117"+
		"\5\uffff\1\56\1\0\2\uffff\2\75\1\uffff\1\60\1\56\1\115\1\114\1\122\1\105"+
		"\2\60\1\104\1\114\1\117\1\125\1\105\1\117\1\104\1\114\1\102\1\104\1\111"+
		"\1\116\2\uffff\1\0\10\uffff\1\60\1\105\1\60\1\111\1\101\2\uffff\2\105"+
		"\1\60\1\105\1\120\1\102\1\122\1\115\1\101\1\125\1\114\1\60\1\115\1\107"+
		"\1\0\2\uffff\1\103\1\uffff\1\116\1\124\1\130\1\122\1\60\1\uffff\1\124"+
		"\1\60\1\114\1\105\1\60\1\124\2\105\1\uffff\1\101\1\60\1\124\1\107\1\105"+
		"\1\60\1\124\1\uffff\1\105\1\uffff\1\105\1\60\1\uffff\1\105\1\123\1\60"+
		"\1\122\1\uffff\3\60\1\uffff\3\60\1\uffff\2\60\1\uffff\1\131\10\uffff\1"+
		"\40\1\uffff";
	static final String DFA14_maxS =
		"\1\172\1\125\2\122\1\116\1\122\1\110\1\122\1\120\2\101\1\116\1\122\1\117"+
		"\5\uffff\1\71\1\uffff\2\uffff\2\75\1\uffff\1\71\1\154\1\115\1\124\1\122"+
		"\1\105\2\172\1\124\1\114\1\117\1\125\1\105\1\117\1\104\1\114\1\102\1\104"+
		"\1\111\1\116\2\uffff\1\uffff\10\uffff\1\172\1\105\1\172\1\111\1\101\2"+
		"\uffff\2\105\1\172\1\105\1\120\1\102\1\122\1\115\1\101\1\125\1\114\1\172"+
		"\1\115\1\107\1\uffff\2\uffff\1\103\1\uffff\1\116\1\124\1\130\1\122\1\172"+
		"\1\uffff\1\124\1\172\1\114\1\105\1\172\1\124\2\105\1\uffff\1\101\1\172"+
		"\1\124\1\107\1\105\1\172\1\124\1\uffff\1\105\1\uffff\1\105\1\172\1\uffff"+
		"\1\105\1\123\1\172\1\122\1\uffff\3\172\1\uffff\3\172\1\uffff\2\172\1\uffff"+
		"\1\131\10\uffff\1\40\1\uffff";
	static final String DFA14_acceptS =
		"\16\uffff\1\27\1\30\1\31\1\32\1\33\2\uffff\1\37\1\40\2\uffff\1\45\24\uffff"+
		"\1\34\1\51\1\uffff\1\36\1\42\1\41\1\44\1\43\1\46\1\47\1\50\5\uffff\1\3"+
		"\1\20\17\uffff\1\35\1\1\1\uffff\1\17\5\uffff\1\23\10\uffff\1\21\7\uffff"+
		"\1\14\1\uffff\1\11\2\uffff\1\10\4\uffff\1\26\3\uffff\1\4\3\uffff\1\6\2"+
		"\uffff\1\16\1\uffff\1\7\1\24\1\2\1\12\1\5\1\25\1\13\1\15\1\uffff\1\22";
	static final String DFA14_specialS =
		"\24\uffff\1\2\33\uffff\1\1\35\uffff\1\0\73\uffff}>";
	static final String[] DFA14_transitionS = {
			"\1\17\1\31\2\uffff\1\31\22\uffff\1\17\1\25\5\uffff\1\24\1\20\1\21\2\uffff"+
			"\1\22\1\23\1\32\1\uffff\12\33\2\uffff\1\27\1\26\1\30\2\uffff\1\13\1\16"+
			"\1\2\1\5\1\16\1\7\2\16\1\4\2\16\1\15\2\16\1\3\1\14\2\16\1\1\1\12\1\10"+
			"\1\11\1\6\3\16\4\uffff\1\16\1\uffff\32\16",
			"\1\35\16\uffff\1\36\1\34",
			"\1\37",
			"\1\40\3\uffff\1\41",
			"\1\42",
			"\1\43\11\uffff\1\45\2\uffff\1\44",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"",
			"",
			"",
			"",
			"",
			"\1\57\1\uffff\12\33",
			"\101\61\32\60\4\61\1\60\1\61\32\60\uff85\61",
			"",
			"",
			"\1\62",
			"\1\64",
			"",
			"\12\57",
			"\1\57\1\uffff\12\33\22\uffff\1\70\37\uffff\1\70",
			"\1\71",
			"\1\72\7\uffff\1\73",
			"\1\74",
			"\1\75",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\100\16\uffff\1\101\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106",
			"\1\107",
			"\1\110",
			"\1\111",
			"\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"",
			"",
			"\60\61\13\116\6\61\32\116\4\61\1\116\1\61\32\116\uff85\61",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\121",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\123",
			"\1\124",
			"",
			"",
			"\1\125",
			"\1\126",
			"\13\16\6\uffff\16\16\1\127\13\16\4\uffff\1\16\1\uffff\32\16",
			"\1\131",
			"\1\132",
			"\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\142",
			"\1\143",
			"\60\61\13\116\6\61\32\116\4\61\1\116\1\61\32\116\uff85\61",
			"",
			"",
			"\1\144",
			"",
			"\1\145",
			"\1\146",
			"\1\147",
			"\1\150",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"",
			"\1\152",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\154",
			"\1\155",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\157",
			"\1\160",
			"\1\161",
			"",
			"\1\162",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\164",
			"\1\165",
			"\1\166",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\170",
			"",
			"\1\171",
			"",
			"\1\172",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"",
			"\1\174",
			"\1\175",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\1\177",
			"",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"\13\16\6\uffff\32\16\4\uffff\1\16\1\uffff\32\16",
			"",
			"\1\u0088",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u0089",
			""
	};

	static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
	static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
	static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
	static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
	static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
	static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
	static final short[][] DFA14_transition;

	static {
		int numStates = DFA14_transitionS.length;
		DFA14_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
		}
	}

	protected class DFA14 extends DFA {

		public DFA14(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 14;
			this.eot = DFA14_eot;
			this.eof = DFA14_eof;
			this.min = DFA14_min;
			this.max = DFA14_max;
			this.accept = DFA14_accept;
			this.special = DFA14_special;
			this.transition = DFA14_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( SUM | CREATE | ON | INDEX | DELETE | WHERE | SELECT | FROM | DROP | INSERT | UPDATE | INTO | VALUES | TABLE | SET | OR | AND | PRIMARYKEY | INT | STRING | DOUBLE | LONG | STR | WS | L_PAREN | R_PAREN | COMMA | MINUS | STR_LITERALS | STRING_LITERAL | NEQ | EQ | LT | LTE | GT | GTE | EOL | DOT | INTEGER_LITERAL | LONG_LITERAL | DOUBLE_LITERAL );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA14_78 = input.LA(1);
						s = -1;
						if ( ((LA14_78 >= '0' && LA14_78 <= ':')||(LA14_78 >= 'A' && LA14_78 <= 'Z')||LA14_78=='_'||(LA14_78 >= 'a' && LA14_78 <= 'z')) ) {s = 78;}
						else if ( ((LA14_78 >= '\u0000' && LA14_78 <= '/')||(LA14_78 >= ';' && LA14_78 <= '@')||(LA14_78 >= '[' && LA14_78 <= '^')||LA14_78=='`'||(LA14_78 >= '{' && LA14_78 <= '\uFFFF')) ) {s = 49;}
						else s = 79;
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA14_48 = input.LA(1);
						s = -1;
						if ( ((LA14_48 >= '0' && LA14_48 <= ':')||(LA14_48 >= 'A' && LA14_48 <= 'Z')||LA14_48=='_'||(LA14_48 >= 'a' && LA14_48 <= 'z')) ) {s = 78;}
						else if ( ((LA14_48 >= '\u0000' && LA14_48 <= '/')||(LA14_48 >= ';' && LA14_48 <= '@')||(LA14_48 >= '[' && LA14_48 <= '^')||LA14_48=='`'||(LA14_48 >= '{' && LA14_48 <= '\uFFFF')) ) {s = 49;}
						else s = 79;
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA14_20 = input.LA(1);
						s = -1;
						if ( ((LA14_20 >= 'A' && LA14_20 <= 'Z')||LA14_20=='_'||(LA14_20 >= 'a' && LA14_20 <= 'z')) ) {s = 48;}
						else if ( ((LA14_20 >= '\u0000' && LA14_20 <= '@')||(LA14_20 >= '[' && LA14_20 <= '^')||LA14_20=='`'||(LA14_20 >= '{' && LA14_20 <= '\uFFFF')) ) {s = 49;}
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 14, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
