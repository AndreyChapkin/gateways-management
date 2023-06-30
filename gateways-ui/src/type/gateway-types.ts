import { DeviceDto } from "./device-types";

export interface GatewayDto {
    id: number,
    serialNumber: string,
    name: string,
    ipv4Address: string,
    devices: DeviceDto[] | null,
}