/**
 *可以在项目根路径直接运行下面命令：
 $ export JAVA_OPTS=-Xmx1024m -XX:MaxPermSize=128M -Djava.security.egd=file:/dev/./urandom
 $ ./gradlew bootRun
 也可以先 build 生成一个 jar 文件，然后执行该 jar 文件：

 $ ./gradlew build && java -jar build/libs/ng-spring-boot-0.0.1-SNAPSHOT.jar

 https://www.cnblogs.com/softidea/p/5631763.html
 */



//mvn -Dmaven.test.skip -U clean package //-U是更新第三方包
//build->plugins-plugin-configuration->mainClass

//http://maven.apache.org/plugins/maven-shade-plugin/examples/executable-jar.html

import com.algorithm.$8_annotation.single.ann.Location;

/**
 * @Location("maven 命令 cmd ")
 *
 *
 * mvn -U 强制更新snapshot类型的插件或依赖库
 *
 * mvn clean install -U
 *
 * 强制让maven检查更新;
 *
 */


/**
 * https://www.cnblogs.com/zhaoyan001/p/8735196.html
 */

//        -am,--also-make                        If project list is specified, also
//        build projects required by the
//        list
//        -amd,--also-make-dependents            If project list is specified, also
//        build projects that depend on
//        projects on the list
//        -B,--batch-mode                        Run in non-interactive (batch)
//        mode
//        -b,--builder <arg>                     The id of the build strategy to
//        use.
//        -C,--strict-checksums                  Fail the build if checksums don't
//        match
//        -c,--lax-checksums                     Warn if checksums don't match
//        -cpu,--check-plugin-updates            Ineffective, only kept for
//        backward compatibility
//        -D,--define <arg>                      Define a system property
//        -e,--errors                            Produce execution error messages
//        -emp,--encrypt-master-password <arg>   Encrypt master security password
//        -ep,--encrypt-password <arg>           Encrypt server password
//        -f,--file <arg>                        Force the use of an alternate POM
//        file (or directory with pom.xml).
//        -fae,--fail-at-end                     Only fail the build afterwards;
//        allow all non-impacted builds to
//        continue
//        -ff,--fail-fast                        Stop at first failure in
//        reactorized builds
//        -fn,--fail-never                       NEVER fail the build, regardless
//        of project result
//        -gs,--global-settings <arg>            Alternate path for the global
//        settings file
//        -gt,--global-toolchains <arg>          Alternate path for the global
//        toolchains file
//        -h,--help                              Display help information
//        -l,--log-file <arg>                    Log file where all build output
//        will go.
//        -llr,--legacy-local-repository         Use Maven 2 Legacy Local
//        Repository behaviour, ie no use of
//        _remote.repositories. Can also be
//        activated by using
//        -Dmaven.legacyLocalRepo=true
//        -N,--non-recursive                     Do not recurse into sub-projects
//        -npr,--no-plugin-registry              Ineffective, only kept for
//        backward compatibility
//        -npu,--no-plugin-updates               Ineffective, only kept for
//        backward compatibility
//        -nsu,--no-snapshot-updates             Suppress SNAPSHOT updates
//        -o,--offline                           Work offline
//        -P,--activate-profiles <arg>           Comma-delimited list of profiles
//        to activate
//        -pl,--projects <arg>                   Comma-delimited list of specified
//        reactor projects to build instead
//        of all projects. A project can be
//        specified by [groupId]:artifactId
//        or by its relative path.
//        -q,--quiet                             Quiet output - only show errors
//        -rf,--resume-from <arg>                Resume reactor from specified
//        project
//        -s,--settings <arg>                    Alternate path for the user
//        settings file
//        -T,--threads <arg>                     Thread count, for instance 2.0C
//        where C is core multiplied
//        -t,--toolchains <arg>                  Alternate path for the user
//        toolchains file
//        -U,--update-snapshots                  Forces a check for missing
//        releases and updated snapshots on
//        remote repositories
//        -up,--update-plugins                   Ineffective, only kept for
//        backward compatibility
//        -V,--show-version                      Display version information
//        WITHOUT stopping build
//        -v,--version                           Display version information
//        -X,--debug                             Produce execution debug output
//        van:~ van$



