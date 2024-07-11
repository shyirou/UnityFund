package models;

public class Project {
    private String judulProject;
    private String latarBelakang;
    private String tujuanKegiatan;
    private String namaTeam;
    private String anggotaTeam;

    public Project(String judulProject, String latarBelakang, String tujuanKegiatan, String namaTeam, String anggotaTeam) {
        this.judulProject = judulProject;
        this.latarBelakang = latarBelakang;
        this.tujuanKegiatan = tujuanKegiatan;
        this.namaTeam = namaTeam;
        this.anggotaTeam = anggotaTeam;
    }

    public String getJudulProject() {
        return judulProject;
    }

    public void setJudulProject(String judulProject) {
        this.judulProject = judulProject;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getTujuanKegiatan() {
        return tujuanKegiatan;
    }

    public void setTujuanKegiatan(String tujuanKegiatan) {
        this.tujuanKegiatan = tujuanKegiatan;
    }

    public String getNamaTeam() {
        return namaTeam;
    }

    public void setNamaTeam(String namaTeam) {
        this.namaTeam = namaTeam;
    }

    public String getAnggotaTeam() {
        return anggotaTeam;
    }

    public void setAnggotaTeam(String anggotaTeam) {
        this.anggotaTeam = anggotaTeam;
    }

    @Override
    public String toString() {
        return "Project{" +
                "judulProject='" + judulProject + '\'' +
                ", latarBelakang='" + latarBelakang + '\'' +
                ", tujuanKegiatan='" + tujuanKegiatan + '\'' +
                ", namaTeam='" + namaTeam + '\'' +
                ", anggotaTeam='" + anggotaTeam + '\'' +
                '}';
    }
}
