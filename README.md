BrainFawkes
===========

Multitarget Optimizing Brainfuck Compiler

This compiler is implemented in Java, and currently targets Java. The code-emitting backend is pluggable, and can be extended by implementing <code>com.drellem.bf.emit.Emitter</code> to add support for different language targets. 

<b>Status</b>
It can currently compile the standard "Hello world!" program.

<b>How to Use</b>
Command-line operation has not been developed yet, but you can run some of the files under <code>com.drellem.bf.test</code> for an example.

<b>How can I help?</b>
Write a class implementing <code>com.drellem.bf.emit.Emitter</code> to output code in your favorite language. It's very easy, and you can look at <code>JavaEmitter</code> as an example.

<b>Stages</b>

Hello world: <code>+++++ +++++             initialize counter (cell #0) to 10[                       use loop to set the next four cells to 70/100/30/10 > +++++ ++              add  7 to cell #1> +++++ +++++           add 10 to cell #2 > +++    > +                     add  1 to cell #4<<<< -                  decrement counter (cell #0)]                   > ++ .                  print 'H'> + .                   print 'e'+++++ ++ .              print 'l'.                       print 'l'+++ .                   print 'o'> ++ .                  print ' '<< +++++ +++++ +++++ .  print 'W'> .                     print 'o'+++ .                   print 'r'----- - .               print 'l'----- --- .             print 'd'> + .                   print '!'> .                     print '
'</code>

<ol>
  <li>Compression</li>
  The Brainfuck code is compressed so that <code>++---<<>>>>>></code> will become <code>-2>></code>.
  Hello world: <code>10+[>7+>10+>3+>+4<-]>2+.>+.7+..3+.>2+.2<15+.>.3+.6-.8-.>+.>.</code>
  
  </li>Lexing</li>
  Tokens are formed from this compressed code.
  Hello world: <code>(PLUS,10)(LOOP,[)(INC,)(PLUS,7)(INC,)(PLUS,10)(INC,)(PLUS,2)(INC,)(PLUS,)(DEC,4)(MINUS,)(END,])(INC,)(PLUS,2)(PUT,.)(INC,)(PLUS,)(PUT,.)(PLUS,7)(PUT,.)(PUT,.)(PLUS,3)(PUT,.)(MINUS,6)(PUT,.)(MINUS,8)(PUT,.)(INC,)(PLUS,)(PUT,.)(INC,)(PUT,.)
</code>.

  <li>Optimizing</li>
  The code is parsed into a syntax tree and various passes are performed.
  Hello world: <code>(PLUS)(LOOPPLUS
PLUS
PLUS
PLUS
MINUS
)(PLUS)(PUT)(PLUS)(PUT)(PLUS)(PUT)(PUT)(PLUS)(PUT)(MINUS)(PUT)(MINUS)(PUT)(PLUS)(PUT)(PUT)(INC)</code>.

  <li>Code generation</li>
  All constants are propogated and code is generated.
  Hello world: <code>public class Main {
  private static byte[] tape = new byte[30000];
	private static int index = 0;
	public static void main(String[] args){
		System.out.print("H");
		System.out.print("e");
		System.out.print("l");
		System.out.print("l");
		System.out.print("o");
		System.out.print(" ");
		System.out.print("W");
		System.out.print("o");
		System.out.print("r");
		System.out.print("l");
		System.out.print("d");
		System.out.print("!");
		System.out.println();
	}
}
</code>
</ol>
