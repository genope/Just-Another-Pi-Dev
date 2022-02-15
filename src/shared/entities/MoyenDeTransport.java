/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shared.entities;

import java.sql.Date;
import shared.entities.enums.TypeDeTransport;

/**
 *
 * @author l3ej
 */
public class MoyenDeTransport extends Offres{

    public MoyenDeTransport() {
    }

    @Override
    public String toString() {
        return "MoyenDeTransport{" + super.toString() + ", matricule=" + matricule + ", typeDeTransport=" + typeDeTransport + '}';
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public TypeDeTransport getTypeDeTransport() {
        return typeDeTransport;
    }

    public void setTypeDeTransport(TypeDeTransport typeDeTransport) {
        this.typeDeTransport = typeDeTransport;
    }
    public int matricule;
    private TypeDeTransport typeDeTransport;

    public MoyenDeTransport(int matricule, TypeDeTransport typeDeTransport, String nom, String description, Date datedebut, Date datefin, float prix, boolean etat, String ville) {
        super(nom, description, datedebut, datefin, prix, etat, ville);
        this.matricule = matricule;
        this.typeDeTransport = typeDeTransport;
    }

    public MoyenDeTransport(int matricule, TypeDeTransport typeDeTransport) {
        this.matricule = matricule;
        this.typeDeTransport = typeDeTransport;
    }

    public MoyenDeTransport(int id_user, int matricule, TypeDeTransport typeDeTransport, String nom, String description, Date datedebut, Date datefin, float prix, boolean etat, String ville) {
        super(id_user, nom, description, datedebut, datefin, prix, etat, ville);
        this.matricule = matricule;
        this.typeDeTransport = typeDeTransport;
    }
    
   
}
