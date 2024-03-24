package com.prj.sbforsvc.User;

import org.springframework.ui.Model;

public interface UserService {

    public boolean checkUser(String email, String password, User user, Model model);

    public RetRegister registerUser(String username, String email, String password, String confirmPassword,
            Model model);

    public User getUser(Long idx);
}
