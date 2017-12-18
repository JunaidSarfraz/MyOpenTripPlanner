/* This program is free software: you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public License
as published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package org.opentripplanner.updater.bike_rental;

import com.fasterxml.jackson.databind.JsonNode;
import org.opentripplanner.routing.bike_rental.BikeRentalStation;
import org.opentripplanner.util.NonLocalizedString;

import java.util.HashSet;


/**
 * Build a BikeRentalStation object from BayAreaBikeShare data source JsonNode object.
 *
 * @see GenericJsonBikeRentalDataSource
 */
public class TelepodBikeRentalDataSource extends GenericJsonBikeRentalDataSource {

    private String networkName;

    public TelepodBikeRentalDataSource(String networkName) {
        super("");
        if (networkName != null && !networkName.isEmpty()) {
            this.networkName = networkName;
        } else {
            this.networkName = "TelepodDev";
        }
    }

    public BikeRentalStation makeStation(JsonNode stationNode) {


        BikeRentalStation brstation = new BikeRentalStation();

        brstation.networks = new HashSet<String>();
        brstation.networks.add(this.networkName);

        brstation.id = stationNode.path("stationId").toString();
        brstation.x = stationNode.path("Lng").asDouble();
        brstation.y = stationNode.path("Lat").asDouble();
        brstation.name =  new NonLocalizedString(stationNode.path("stationName").asText());
        brstation.bikesAvailable = stationNode.path("scooterAvail").asInt();
        brstation.spacesAvailable = stationNode.path("dockingAvail").asInt();

        return brstation;
    }
}
