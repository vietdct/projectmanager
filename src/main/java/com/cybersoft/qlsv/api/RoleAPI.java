package com.cybersoft.qlsv.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cybersoft.qlsv.converter.RoleConverter;
import com.cybersoft.qlsv.dto.RoleDTO;
import com.cybersoft.qlsv.entity.RoleEntity;
import com.cybersoft.qlsv.entity.UserEntity;
import com.cybersoft.qlsv.implement.RoleServiceImpl;
import com.cybersoft.qlsv.repository.RoleRepository;
import com.cybersoft.qlsv.repository.UserRepository;
import com.cybersoft.qlsv.service.RoleService;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/role-table")
public class RoleAPI {

    private final RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleConverter roleConverter;

    RoleAPI(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping({""})
    public String showRoleTable(Model model) {
        model.addAttribute("listRoleName", roleService.getRoleName());
    return "role-table"; // -> /WEB-INF/views/role-table.jsp
    }    

//-------------------------------------------DELETE ROLE--------------------------------------------------
    @DeleteMapping("/delete/{id}")
    public String deleteShow(@PathVariable("id") Long id, RedirectAttributes ra ,@ModelAttribute RoleDTO rDto, Authentication authentication){
        rDto.setRoleId(id);
          if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            ra.addFlashAttribute("errorRole", "You need to log in to delete a role.");
        return "redirect:/role-table"; 
    }
        boolean isAdmin = authentication.getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(!isAdmin){
            ra.addFlashAttribute("errorMsg", "You don't have permission to delete");
            return "redirect:/404";
        }
    try {
        roleService.deleteRole(id); // gọi service xóa role theo id
        ra.addFlashAttribute("successMsg", "Delete succeeded");
        return "redirect:/role-table";
    } catch (Exception e) {
        // 4) Dính FK: còn user dùng role này → lấy list user và báo lỗi đẹp
        long countUser = userRepository.countByRole_Id(id);
        if (countUser > 0) {
            List<String> listUser = userRepository.findUserNamesByRoleId(id);
            String list = listUser.stream().limit(5).collect(java.util.stream.Collectors.joining(", "));
            if (listUser.size() > 5) {
                list += " ... and " + (listUser.size() - 5) + " more people";
            }
            ra.addFlashAttribute("errorMsg",
                "Can't delete Because user is still : " + list);
        } else {
            // Phòng trường hợp khác (không phải do FK)
            ra.addFlashAttribute("errorMsg", "Delete failed (unexpected error).");
        }
        return "redirect:/role-table";
    } 

    }
}
