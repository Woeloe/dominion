package be.ugent.dominion.client;

/**
 * Het gedeelte van de 'supply' dat actiekaarten bevat. We beginnen met 10 actiekaarten, behalve wanneer
 * de kaart stijl 'victory' want dan hangt het aantal af van het aantal spelers.
 */
public class Kingdom extends SupplyPanel {

    public Kingdom(String[] set, Client client) {
        super(set, 5, 1.0, client);
    }

}
