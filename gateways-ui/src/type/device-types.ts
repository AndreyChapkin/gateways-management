export type DeviceStatus = "ONLINE" | "OFFLINE";

export interface DeviceDto {
    id: number | null,
    uid: number,
    vendor: string,
    createDate: string | null,
    status: DeviceStatus,
}