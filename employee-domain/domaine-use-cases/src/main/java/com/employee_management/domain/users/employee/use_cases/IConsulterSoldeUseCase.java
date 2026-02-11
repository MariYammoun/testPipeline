package com.employee_management.domain.users.employee.use_cases;

/**
 * @author $(USER)
 **/
public interface IConsulterSoldeUseCase {

    /**
     * Récupère le solde de congés d'un utilisateur.
     * @param employeeId L'identifiant de l'utilisateur
     * @return Le nombre de jours restants (int)
     */
    int execute(Long employeeId);
}
