/////////////////////////////////////////////////////////////////
//
// Copyright (C) 2009 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License,
// or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
// or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
// for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library; if not, write to the Free Software Foundation, Inc.,
// 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
//
/////////////////////////////////////////////////////////////////

import java.util.List;

import affymetrix.fusion.cdf.FusionCDFData;
import affymetrix.fusion.cdf.FusionCDFProbeGroupInformation;
import affymetrix.fusion.cdf.FusionCDFProbeInformation;
import affymetrix.fusion.cdf.FusionCDFProbeSetInformation;
import affymetrix.fusion.cel.FusionCELData;

public class read_cel {

	public read_cel() {
	}

	/**
	 * @param args
	 *          the command line arguments
	 */
	public static void main(String[] args) {

		String celFileName = args[0];
		String cdfFileName = args[1];
		FusionCELData cel;
		FusionCDFData cdf;
		try {
			cel = new FusionCELData();
			cel.setFileName(celFileName);
			if (cel.read() == false) {
				System.out.println("Failed to read the CEL file.");
				return;
			}
			
			cdf = new FusionCDFData();
			cdf.setFileName(cdfFileName);
			if (cdf.read() == false) {
				System.out.println("Failed to read the CDF file.");
				return;
			}

			if (cel.isMultiColor() == true)
				System.out.println("The CEL file has multiple channels.");
			else
				System.out.println("The CEL file has one channel.");

			read_cel rc = new read_cel();
			rc.readSingleAndMultiChannelCelFile(cel, cdf);
			if (cel.isMultiColor() == true) {
				rc.readSingleChannelCelFile(cel, cdf);
			}
			
			
		}
		catch (Exception e) {
		}
	}
	
	
	
	public void readSingleAndMultiChannelCelFile(FusionCELData cel, FusionCDFData cdf)
	{
		List<String> listofChannels = cel.getChannels();
		for (int i = 0; i<listofChannels.size(); ++i)
		{
			System.out.println("Reading  channel " + i + ": " + listofChannels.get(i) );  //channel name
			cel.setActiveDataGroup(listofChannels.get(i));
			float sum = 0;
			int n = cel.getCells();
			try {
				for (int j = 0; j < n; j++) {
					sum += cel.getIntensity(i);
				}
				float avg = sum / n;
				System.out.println("The average intensity is " + avg);
	
				int nsets = cdf.getHeader().getNumProbeSets();
				for (int iset = 0; iset < nsets; iset++) {
					sum = 0;
					FusionCDFProbeSetInformation set = new FusionCDFProbeSetInformation();
					cdf.getProbeSetInformation(iset, set);
					int ngroups = set.getNumGroups();
					for (int igroup = 0; igroup < ngroups; igroup++) {
						FusionCDFProbeGroupInformation group = new FusionCDFProbeGroupInformation();
						set.getGroup(igroup, group);
						int ncells = group.getNumCells();
						for (int icell = 0; icell < ncells; icell++) {
							FusionCDFProbeInformation probe = new FusionCDFProbeInformation();
							group.getCell(icell, probe);
							sum += cel.getIntensity(probe.getX(), probe.getY());
						}
					}
					avg = sum / set.getNumCells();
					System.out.println("The average probe set intensity is " + avg);
				}
			} catch (Throwable t) {
				System.out.println(t.getMessage());
			}
		}
	}
	
	// This is the code sort of as it was 
	public void readSingleChannelCelFile(FusionCELData cel, FusionCDFData cdf)
	{
		float sum = 0;
		try {
			int n = cel.getCells();
			for (int i = 0; i < n; i++) {
				sum += cel.getIntensity(i);
			}
			float avg = sum / n;
			System.out.println("The average intensity is " + avg);
	
			int nsets = cdf.getHeader().getNumProbeSets();
			for (int iset = 0; iset < nsets; iset++) {
				sum = 0;
				FusionCDFProbeSetInformation set = new FusionCDFProbeSetInformation();
				cdf.getProbeSetInformation(iset, set);
				int ngroups = set.getNumGroups();
				for (int igroup = 0; igroup < ngroups; igroup++) {
					FusionCDFProbeGroupInformation group = new FusionCDFProbeGroupInformation();
					set.getGroup(igroup, group);
					int ncells = group.getNumCells();
					for (int icell = 0; icell < ncells; icell++) {
						FusionCDFProbeInformation probe = new FusionCDFProbeInformation();
						group.getCell(icell, probe);
						sum += cel.getIntensity(probe.getX(), probe.getY());
					}
				}
				avg = sum / set.getNumCells();
				System.out.println("The average probe set intensity is " + avg);
			}
		}
		catch (Exception e) {
		}
	}
}
