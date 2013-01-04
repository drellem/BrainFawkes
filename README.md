BrainFawkes
===========

Multitarget Optimizing Brainfuck Compiler

This compiler is implemented in Java, and currently targets Java. The code-emitting backend is pluggable, and can be extended by implementing <code>com.drellem.bf.emit.Emitter</code> to add support for different language targets. Development is in progress!

<b>Stages</b>

Hello world: <code>++++++++++ Initialize counter[>+++++ ++>+++++ +++++>++>+<<<<-]>++.>+.+++++ ++..+++.----- -.----- ---.>+.>.</code>

<ol>
  <li>Compression</li>
  The Brainfuck code is compressed so that <code>++---<<>>>>>></code> will become <code>-2>></code>.
  Hello world: <code>10+[>7+>10+>2+>+4<-]>2+.>+.7+..3+.6-.8-.>+.>.</code>
  
  </li>Lexing</li>
  Tokens are formed from this compressed code.
  Hello world: <code>Token type:PLUS value:10
Token type:LOOP value:[
Token type:INC value:
Token type:PLUS value:7
Token type:INC value:
Token type:PLUS value:10
Token type:INC value:
Token type:PLUS value:2
Token type:INC value:
Token type:PLUS value:
Token type:DEC value:4
Token type:MINUS value:
Token type:END value:]
Token type:INC value:
Token type:PLUS value:2
Token type:PUT value:.
Token type:INC value:
Token type:PLUS value:
Token type:PUT value:.
Token type:PLUS value:7
Token type:PUT value:.
Token type:PUT value:.
Token type:PLUS value:3
Token type:PUT value:.
Token type:MINUS value:6
Token type:PUT value:.
Token type:MINUS value:8
Token type:PUT value:.
Token type:INC value:
Token type:PLUS value:
Token type:PUT value:.
Token type:INC value:
Token type:PUT value:.</code>.

  <li>Optimizing</li>
  The code is parsed into a syntax tree and various passes are performed.
  Hello world: <code>Type:PLUS
Type:LOOP
->Type:PLUS
->Type:PLUS
->Type:PLUS
->Type:PLUS
->Type:MINUS
->Type:INC
Type:PLUS
Type:PUT
Type:PLUS
Type:PUT
Type:PLUS
Type:PUT
Type:PUT
Type:PLUS
Type:PUT
Type:PUT
Type:PUT
Type:PLUS
Type:PUT
Type:PUT
Type:INC</code>.

  <li>Code generation</li>
  All constants are propogated and code is generated.
</ol>
