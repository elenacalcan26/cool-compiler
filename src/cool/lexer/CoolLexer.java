// Generated from C:/Users/Elena/Desktop/AN4/sem1/CPL/teme/cool-compiler/src/cool/lexer\CoolLexer.g4 by ANTLR 4.10.1

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, CLASS=2, IF=3, FI=4, IN=5, INHERITS=6, ISVOID=7, LET=8, LOOP=9, 
		POOL=10, THEN=11, WHILE=12, CASE=13, ESAC=14, NEW=15, OF=16, NOT=17, LPAREN=18, 
		RPAREN=19, LBRACE=20, RBRACE=21, COLON=22, SEMICOLON=23, COMMA=24, DOT=25, 
		NEG=26, ASSIGN=27, PLUS=28, MINUS=29, MUL=30, DIV=31, EQUAL=32, LT=33, 
		LE=34, TYPE=35, WS=36;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"CLASS", "IF", "FI", "IN", "INHERITS", "ISVOID", "LET", "LOOP", "POOL", 
			"THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "COLON", "SEMICOLON", "COMMA", "DOT", "NEG", "ASSIGN", 
			"PLUS", "MINUS", "MUL", "DIV", "EQUAL", "LT", "LE", "DIGIT", "LETTER", 
			"UPPER_LETTER", "TYPE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'class'", "'if'", "'fi'", "'IN'", "'inherits'", "'isvoid'", 
			"'let'", "'loop'", "'pool'", "'then'", "'while'", "'case'", "'esac'", 
			"'new'", "'of'", "'not'", "'('", "')'", "'{'", "'}'", "':'", "';'", "','", 
			"'.'", "'~'", "'<-'", "'+'", "'-'", "'*'", "'/'", "'='", "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "CLASS", "IF", "FI", "IN", "INHERITS", "ISVOID", "LET", 
			"LOOP", "POOL", "THEN", "WHILE", "CASE", "ESAC", "NEW", "OF", "NOT", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "COLON", "SEMICOLON", "COMMA", 
			"DOT", "NEG", "ASSIGN", "PLUS", "MINUS", "MUL", "DIV", "EQUAL", "LT", 
			"LE", "TYPE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	    
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000$\u00cd\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001"+
		" \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001%\u0004"+
		"%\u00c8\b%\u000b%\f%\u00c9\u0001%\u0001%\u0000\u0000&\u0001\u0002\u0003"+
		"\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b\u0007\r\b\u000f\t\u0011\n"+
		"\u0013\u000b\u0015\f\u0017\r\u0019\u000e\u001b\u000f\u001d\u0010\u001f"+
		"\u0011!\u0012#\u0013%\u0014\'\u0015)\u0016+\u0017-\u0018/\u00191\u001a"+
		"3\u001b5\u001c7\u001d9\u001e;\u001f= ?!A\"C\u0000E\u0000G\u0000I#K$\u0001"+
		"\u0000\u0004\u0001\u000009\u0002\u0000AZaz\u0001\u0000AZ\u0003\u0000\t"+
		"\n\f\r  \u00ca\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000"+
		"\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%"+
		"\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000"+
		"\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000"+
		"A\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001"+
		"\u0000\u0000\u0000\u0001M\u0001\u0000\u0000\u0000\u0003S\u0001\u0000\u0000"+
		"\u0000\u0005V\u0001\u0000\u0000\u0000\u0007Y\u0001\u0000\u0000\u0000\t"+
		"\\\u0001\u0000\u0000\u0000\u000be\u0001\u0000\u0000\u0000\rl\u0001\u0000"+
		"\u0000\u0000\u000fp\u0001\u0000\u0000\u0000\u0011u\u0001\u0000\u0000\u0000"+
		"\u0013z\u0001\u0000\u0000\u0000\u0015\u007f\u0001\u0000\u0000\u0000\u0017"+
		"\u0085\u0001\u0000\u0000\u0000\u0019\u008a\u0001\u0000\u0000\u0000\u001b"+
		"\u008f\u0001\u0000\u0000\u0000\u001d\u0093\u0001\u0000\u0000\u0000\u001f"+
		"\u0096\u0001\u0000\u0000\u0000!\u009a\u0001\u0000\u0000\u0000#\u009c\u0001"+
		"\u0000\u0000\u0000%\u009e\u0001\u0000\u0000\u0000\'\u00a0\u0001\u0000"+
		"\u0000\u0000)\u00a2\u0001\u0000\u0000\u0000+\u00a4\u0001\u0000\u0000\u0000"+
		"-\u00a6\u0001\u0000\u0000\u0000/\u00a8\u0001\u0000\u0000\u00001\u00aa"+
		"\u0001\u0000\u0000\u00003\u00ac\u0001\u0000\u0000\u00005\u00af\u0001\u0000"+
		"\u0000\u00007\u00b1\u0001\u0000\u0000\u00009\u00b3\u0001\u0000\u0000\u0000"+
		";\u00b5\u0001\u0000\u0000\u0000=\u00b7\u0001\u0000\u0000\u0000?\u00b9"+
		"\u0001\u0000\u0000\u0000A\u00bb\u0001\u0000\u0000\u0000C\u00be\u0001\u0000"+
		"\u0000\u0000E\u00c0\u0001\u0000\u0000\u0000G\u00c2\u0001\u0000\u0000\u0000"+
		"I\u00c4\u0001\u0000\u0000\u0000K\u00c7\u0001\u0000\u0000\u0000MN\u0005"+
		"c\u0000\u0000NO\u0005l\u0000\u0000OP\u0005a\u0000\u0000PQ\u0005s\u0000"+
		"\u0000QR\u0005s\u0000\u0000R\u0002\u0001\u0000\u0000\u0000ST\u0005i\u0000"+
		"\u0000TU\u0005f\u0000\u0000U\u0004\u0001\u0000\u0000\u0000VW\u0005f\u0000"+
		"\u0000WX\u0005i\u0000\u0000X\u0006\u0001\u0000\u0000\u0000YZ\u0005I\u0000"+
		"\u0000Z[\u0005N\u0000\u0000[\b\u0001\u0000\u0000\u0000\\]\u0005i\u0000"+
		"\u0000]^\u0005n\u0000\u0000^_\u0005h\u0000\u0000_`\u0005e\u0000\u0000"+
		"`a\u0005r\u0000\u0000ab\u0005i\u0000\u0000bc\u0005t\u0000\u0000cd\u0005"+
		"s\u0000\u0000d\n\u0001\u0000\u0000\u0000ef\u0005i\u0000\u0000fg\u0005"+
		"s\u0000\u0000gh\u0005v\u0000\u0000hi\u0005o\u0000\u0000ij\u0005i\u0000"+
		"\u0000jk\u0005d\u0000\u0000k\f\u0001\u0000\u0000\u0000lm\u0005l\u0000"+
		"\u0000mn\u0005e\u0000\u0000no\u0005t\u0000\u0000o\u000e\u0001\u0000\u0000"+
		"\u0000pq\u0005l\u0000\u0000qr\u0005o\u0000\u0000rs\u0005o\u0000\u0000"+
		"st\u0005p\u0000\u0000t\u0010\u0001\u0000\u0000\u0000uv\u0005p\u0000\u0000"+
		"vw\u0005o\u0000\u0000wx\u0005o\u0000\u0000xy\u0005l\u0000\u0000y\u0012"+
		"\u0001\u0000\u0000\u0000z{\u0005t\u0000\u0000{|\u0005h\u0000\u0000|}\u0005"+
		"e\u0000\u0000}~\u0005n\u0000\u0000~\u0014\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0005w\u0000\u0000\u0080\u0081\u0005h\u0000\u0000\u0081\u0082\u0005"+
		"i\u0000\u0000\u0082\u0083\u0005l\u0000\u0000\u0083\u0084\u0005e\u0000"+
		"\u0000\u0084\u0016\u0001\u0000\u0000\u0000\u0085\u0086\u0005c\u0000\u0000"+
		"\u0086\u0087\u0005a\u0000\u0000\u0087\u0088\u0005s\u0000\u0000\u0088\u0089"+
		"\u0005e\u0000\u0000\u0089\u0018\u0001\u0000\u0000\u0000\u008a\u008b\u0005"+
		"e\u0000\u0000\u008b\u008c\u0005s\u0000\u0000\u008c\u008d\u0005a\u0000"+
		"\u0000\u008d\u008e\u0005c\u0000\u0000\u008e\u001a\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0005n\u0000\u0000\u0090\u0091\u0005e\u0000\u0000\u0091\u0092"+
		"\u0005w\u0000\u0000\u0092\u001c\u0001\u0000\u0000\u0000\u0093\u0094\u0005"+
		"o\u0000\u0000\u0094\u0095\u0005f\u0000\u0000\u0095\u001e\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0005n\u0000\u0000\u0097\u0098\u0005o\u0000\u0000\u0098"+
		"\u0099\u0005t\u0000\u0000\u0099 \u0001\u0000\u0000\u0000\u009a\u009b\u0005"+
		"(\u0000\u0000\u009b\"\u0001\u0000\u0000\u0000\u009c\u009d\u0005)\u0000"+
		"\u0000\u009d$\u0001\u0000\u0000\u0000\u009e\u009f\u0005{\u0000\u0000\u009f"+
		"&\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005}\u0000\u0000\u00a1(\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0005:\u0000\u0000\u00a3*\u0001\u0000\u0000"+
		"\u0000\u00a4\u00a5\u0005;\u0000\u0000\u00a5,\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0005,\u0000\u0000\u00a7.\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005"+
		".\u0000\u0000\u00a90\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005~\u0000"+
		"\u0000\u00ab2\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005<\u0000\u0000\u00ad"+
		"\u00ae\u0005-\u0000\u0000\u00ae4\u0001\u0000\u0000\u0000\u00af\u00b0\u0005"+
		"+\u0000\u0000\u00b06\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005-\u0000"+
		"\u0000\u00b28\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005*\u0000\u0000\u00b4"+
		":\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005/\u0000\u0000\u00b6<\u0001"+
		"\u0000\u0000\u0000\u00b7\u00b8\u0005=\u0000\u0000\u00b8>\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0005<\u0000\u0000\u00ba@\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005<\u0000\u0000\u00bc\u00bd\u0005=\u0000\u0000\u00bdB\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0007\u0000\u0000\u0000\u00bfD\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0007\u0001\u0000\u0000\u00c1F\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\u0007\u0002\u0000\u0000\u00c3H\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c5\u0003G#\u0000\u00c5J\u0001\u0000\u0000\u0000\u00c6\u00c8"+
		"\u0007\u0003\u0000\u0000\u00c7\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cc"+
		"\u0006%\u0000\u0000\u00ccL\u0001\u0000\u0000\u0000\u0002\u0000\u00c9\u0001"+
		"\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}