package szz.study.springboot3;

import com.cssc.cloud.starter.mybatis.plus.config.GeneratorConfig;
import com.cssc.cloud.starter.mybatis.plus.generator.CodeGenerator;

/**
 * @author Administrator
 */
public class Generator {
    public static void main(String[] args) {
        GeneratorConfig config = GeneratorConfig.builder()
                .author("szz")
                .modulePath("/springboot3")
                .modulePackage("szz.study.springboot3.system")
                .mysql(
                        GeneratorConfig.Mysql.builder()
                                .jdbc("jdbc:mysql://192.168.1.52:3306/auth?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                                .username("root")
                                .password("123456")
                                .build()
                )
                .table(GeneratorConfig.Table.builder()
                        .tables(
                                "sys_dict_data",
                                "sys_dict_type",
                                "sys_menu",
                                "sys_role",
                                "sys_role_menu",
                                "sys_user",
                                "sys_user_role"
                        )
                        .tablesPrefix()
                        .build()
                ).build();
        CodeGenerator.generator(config);
    }
}
