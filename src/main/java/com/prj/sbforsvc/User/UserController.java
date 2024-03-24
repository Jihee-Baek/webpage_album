package com.prj.sbforsvc.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prj.sbforsvc.Member.Group;
import com.prj.sbforsvc.Member.GroupService;
import com.prj.sbforsvc.Member.Member;
import com.prj.sbforsvc.Member.MemberService;
import com.prj.sbforsvc.Member.Relation;
import com.prj.sbforsvc.Member.RelationService;

import org.springframework.ui.Model;

@Controller
public class UserController {

    UserService userService;
    MemberService memberService;
    GroupService groupService;
    RelationService relationService;

    public UserController(UserService userService, MemberService memberService, GroupService groupService,
            RelationService relationService) {
        this.userService = userService;
        this.memberService = memberService;
        this.groupService = groupService;
        this.relationService = relationService;
    }

    @GetMapping("/")
    public String test() {
        return "Home";
    }

    @GetMapping("/signup")
    public String registerPage() {
        return "Signup";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm_password") String confirmPassword,
            Model model) {

        RetRegister ret = userService.registerUser(username, email, password, confirmPassword, model);
        if (ret == RetRegister.SUCCESS) {
            return "Login";
        } else if (ret == RetRegister.DIFFPW) {
            return "Signup";
        } else {
            return "redirect:/signup";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "Login"; // 로그인 페이지의 HTML 파일명
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        User user = new User();
        if (userService.checkUser(email, password, user, model)) {
            Long idx = user.getIdx();
            String strIdx = Long.toString(idx);
            return "redirect:/main/" + strIdx;
        } else {
            return "Login";
        }

    }

    @GetMapping("/main/{idx}")
    public String mainPerUser(Model model, @PathVariable("idx") Long idx) {
        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        memberService.SetUserInfo(model, user);

        return "Main";
    }

    @GetMapping("/management/{idx}")
    public String addPerUser(Model model, @PathVariable("idx") Long idx) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        return "Add";
    }

    @GetMapping("/management/{idx}/group")
    public String showManageForm(Model model, @PathVariable("idx") Long idx,
            @RequestParam(required = false, value = "re") String re) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        memberService.SetUserInfo(model, user);

        if (re != null) {
            model.addAttribute("addedgroup", true);
            model.addAttribute("addedgroupmessage", "Added Group Successful");
        }

        return "AddPage";
    }

    @PostMapping("/management/{idx}/group")
    public String addPerUserGroup(Model model, @PathVariable("idx") Long idx,
            @RequestParam("add-group") String str_group) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        Group group = new Group();
        group.setName(str_group);
        group.setUser(user);

        boolean b = groupService.addGroupPerUser(model, group);

        return "redirect:/management/" + idx + "/group?re=" + b;
    }

    @GetMapping("/management/{idx}/relation")
    public String showRelationForm(Model model, @PathVariable("idx") Long idx,
            @RequestParam(required = false, value = "re") String re) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        memberService.SetUserInfo(model, user);

        if (re != null) {
            model.addAttribute("addedrelation", true);
            model.addAttribute("addedrelationmessage", "Added Relation Successful");
        }
        return "AddPage";
    }

    @PostMapping("/management/{idx}/relation")
    public String addPerUserRelation(Model model, @PathVariable("idx") Long idx,
            @RequestParam("add-relation") String str_relation) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        Relation relation = new Relation();
        relation.setName(str_relation);
        relation.setUser(user);

        boolean b = relationService.addRelationPerUser(model, relation);

        return "redirect:/management/" + idx + "/relation?re=" + b;
    }

    @GetMapping("/management/{idx}/member")
    public String showMemberForm(Model model, @PathVariable("idx") Long idx,
            @RequestParam(required = false, value = "re") String re) {

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        memberService.SetUserInfo(model, user);

        if (re != null) {
            model.addAttribute("addedmember", true);
            model.addAttribute("addedmembermessage", "Added Member Successful");
        }
        return "AddPage";
    }

    @PostMapping("/management/{idx}/member")
    public String addPerUserMember(Model model, @PathVariable("idx") Long idx,
            @RequestParam(value = "add-member") String str_member, @RequestParam(value = "groupidx") Long groupidx,
            @RequestParam(value = "relationidx") Long relationidx) {

        System.out.println("groupidx:" + groupidx + " relationidx: " + relationidx);

        User user = userService.getUser(idx);
        model.addAttribute("user", user);

        Group group = groupService.getGroup(groupidx);
        Relation relation = relationService.getRelation(relationidx);

        Member member = new Member();
        member.setName(str_member);
        member.setUser(user);
        member.setGroup(group);
        member.setRelation(relation);

        boolean b = memberService.addMemberPerUser(model, member);

        return "redirect:/management/" + idx + "/member?re=" + b;
    }
}
