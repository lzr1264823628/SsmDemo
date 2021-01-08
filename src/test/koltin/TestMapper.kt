import com.crm.domain.mappers.StudentMapper
import com.crm.domain.mappers.UserMapper
import com.crm.domain.pojo.Student
import com.crm.domain.pojo.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.session.web.http.HeaderHttpSessionIdResolver
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(
    SpringJUnit4ClassRunner::class
)
@ContextConfiguration("classpath:applicationContext-dao.xml")
open class TestMapper {

    @Autowired
    var StudentMapper:StudentMapper?=null
    @Autowired
    var userMapper:UserMapper?=null
    @Test
    fun test() {
        println(StudentMapper?.findAll())
    }
    @Test
    fun testAdd(){
        val Stu = Student()
        Stu.cLass = "1808042"
        Stu.name = "testName"
        Stu.dorm = "10409"
        Stu.gender = false
        Stu.department = "软件工程"
        Stu.telephone = "11111111"
        Stu.stuNum = "180804210"
        StudentMapper?.add(Stu)
    }

    @Test
    fun testFind(){
        val a = Student(sId=null, name="test", stuNum="180804210", cLass="1808042", dorm="10409", department="软件工程", telephone="12345678900")
        println(StudentMapper?.find(a))
    }
    @Test
    fun testDel(){
        println(StudentMapper?.deleteById(37))
    }
//    @Test
//    fun addUser(){
//        val user = User(username = "admin",password = "qwe123")
//        println(user.password)
//        userMapper?.add(user)
//    }
}