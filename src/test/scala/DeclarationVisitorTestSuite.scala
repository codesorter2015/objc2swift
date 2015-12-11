import org.junit.runner.RunWith
import org.objc2swift.converter.ObjCParser.ClassNameContext
import org.objc2swift.converter._
import org.scalatest.junit.JUnitRunner

/**
 * Created by takesano on 15/12/09.
 */
@RunWith(classOf[JUnitRunner])
class DeclarationVisitorTestSuite extends ObjC2SwiftTestSuite {
  override def converter(parser: ObjCParser): ObjC2SwiftBaseConverter =
    new ObjC2SwiftBaseConverter
      with DeclarationVisitor
      with TypeVisitor
      with TerminalNodeVisitor
    {
      override def getResult() = visit(parser.declaration())
      override def visitClassName(ctx: ClassNameContext): String = ctx.getText
    }

  test("var decl without init") {
    val source = "MyType x;"
    val expected = "var x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("multiple var decl without init") {
    val source = "MyType x, y;"
    val expected =
      s"""
         |var x: MyType
         |var y: MyType
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("var decl with initializer") {
    val source = "MyType x = 1;"
    val expected = "var x: MyType = 1" // TODO emit type when initialized
    assertConvertSuccess(source, expected)
  }

  test("multiple var decl with init") {
    val source = "MyType x = 1, y = 2;"
    val expected =
      s"""
         |var x: MyType = 1
         |var y: MyType = 2
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("object-type var decl without init") {
    val source = "NSObject *x;"
    val expected = "var x: NSObject"
    assertConvertSuccess(source, expected)
  }

  test("multiple object-type var decl without init") {
    val source = "NSObject *x, *y;"
    val expected =
      s"""
         |var x: NSObject
         |var y: NSObject
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("const var decl without init") {
    val source = "const MyType x;"
    val expected = "let x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("multiple const var decl without init") {
    val source = "const MyType x, y;"
    val expected =
      s"""
         |let x: MyType
         |let y: MyType
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("const var decl with init") {
    val source = "const MyType x = 1;"
    val expected = "let x: MyType = 1"
    assertConvertSuccess(source, expected)
  }

  test("multiple const var decl with init") {
    val source = "const MyType x = 1, y = 2;"
    val expected =
      s"""
         |let x: MyType = 1
         |let y: MyType = 2
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  ignore("const object-type var decl without init") {
    val source = "MyType *const x;"
    val expected = "let x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("static var decl without init") {
    val source = "static MyType x;"
    val expected = "static var x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("multiple static var decl without init") {
    val source = "static MyType x, y;"
    val expected =
      s"""
         |static var x: MyType
         |static var y: MyType
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("static var decl with initializer") {
    val source = "static MyType x = 1;"
    val expected = "static var x: MyType = 1" // TODO emit type when initialized
    assertConvertSuccess(source, expected)
  }

  test("multiple static var decl with init") {
    val source = "static MyType x = 1, y = 2;"
    val expected =
      s"""
         |static var x: MyType = 1
         |static var y: MyType = 2
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("static const var decl without init") {
    val source = "static const MyType x;"
    val expected = "static let x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("multiple static const var decl without init") {
    val source = "static const MyType x, y;"
    val expected =
      s"""
         |static let x: MyType
         |static let y: MyType
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  test("static const var decl with init") {
    val source = "static const MyType x = 1;"
    val expected = "static let x: MyType = 1"
    assertConvertSuccess(source, expected)
  }

  test("multiple static const var decl with init") {
    val source = "static const MyType x = 1, y = 2;"
    val expected =
      s"""
         |static let x: MyType = 1
         |static let y: MyType = 2
       """.stripMargin
    assertConvertSuccess(source, expected)
  }

  ignore("static const object-type var decl without init") {
    val source = "static MyType *const x;"
    val expected = "static let x: MyType"
    assertConvertSuccess(source, expected)
  }

  test("unsigned var decl") {
    val source = "unsigned x;"
    val expected = "var x: UInt"
    assertConvertSuccess(source, expected)
  }

  test("unsigned int var decl") {
    val source = "unsigned int x;"
    val expected = "var x: UInt32"
    assertConvertSuccess(source, expected)
  }

  test("long long var decl") {
    val source = "long long x;"
    val expected = "var x: Int64"
    assertConvertSuccess(source, expected)
  }

  test("unsigned long long var decl") {
    val source = "unsigned long long x;"
    val expected = "var x: UInt64"
    assertConvertSuccess(source, expected)
  }

  // TODO
  ignore("c-type array var decl without init") {
    val source = "MyType x[];"
    val expected = "var x: [MyType]"
    assertConvertSuccess(source, expected)
  }
}
