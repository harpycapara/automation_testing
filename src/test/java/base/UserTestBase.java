package base;

import base.entity.User;
import base.utils.GsonUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserTestBase extends TestBase{
    protected User getExpectedUserFromFile() throws IOException {
        String expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/expectedUser.json")));
        User user = GsonUtil.fromJson(expectedJson,User.class);
        return user;
    }
}
